import greenfoot.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Plataforma que se move na vertical quando é ativada por um computador.
 * Leva consigo as personagens que estiverem em cima, espera um pouco
 * e volta à posição inicial.
 */
public class MovingPlatform extends Solid implements Activatable
{
    private int moveY;
    private int startY;
    private int timer;
    private boolean moving;
    private boolean returning;
    //Cria a plataforma, moveY é o deslocamento em pixels
    public MovingPlatform(int moveY) {
        super();
        this.moveY = moveY;
        startY = 0;
        timer = 0;
        moving = false;
        returning = false;

        setImage(Settings.PLATFORM_IMAGE);
    }
    //Guarda a posição inicial quando a plataforma é adicionada no mundo
    @Override
    public void addedToWorld(World world) {
        startY = getY();
    }
    
    @Override
    public void act() {
        movePlatform();
    }
    //Começa a mover a plataforma
    @Override
    public void activate() {
        if (!moving && moveY != 0) {
            moving = true;
            returning = false;
            timer = 0;
        }
    }
    //Vai até ao destino, espera e volta ao início
    private void movePlatform() {
        if (!moving) return;

        if (timer > 0) {
            timer--;
            return;
        }

        int targetY = returning ? startY : startY + moveY;

        if (getY() != targetY) {
            moveBy(Math.max(-Settings.PLATFORM_SPEED, Math.min(Settings.PLATFORM_SPEED, targetY - getY())));
        }
        else if (returning) {
            moving = false;
            returning = false;
        }
        else {
            returning = true;
            timer = Settings.PLATFORM_WAIT_TIME;
        }
    }
    //Move a plataforma e a personagen que está em cima
    private void moveBy(int step) {
        List<GameCharacter> riders = findRiders();
        setLocation(getX(), getY() + step);

        for (GameCharacter rider : riders) {
            rider.setLocation(rider.getX(), rider.getY() + step);
        }
    }
    //Devolve as personagens que têm os pés em cima da plataforma
    private List<GameCharacter> findRiders() {
        List<GameCharacter> riders = new ArrayList<>();
        int top = getY() - getImage().getHeight() / 2;

        for (GameCharacter character : getWorld().getObjects(GameCharacter.class)) {
            int reach = (character.getImage().getWidth() + getImage().getWidth()) / 2;

            boolean onTop = Math.abs(character.getFeetY() - top) <= Settings.PLATFORM_RIDER_TOLERANCE;
            boolean overlapping = Math.abs(character.getX() - getX()) < reach;

            if (onTop && overlapping) {
                riders.add(character);
            }
        }
        return riders;
    }
}

