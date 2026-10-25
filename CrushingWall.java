import greenfoot.*;
/**
 * Parede que esmaga: espera, desce depressa, pára e volta a subir devagar.
 * Tira uma vida aos agentes que tocar.
 */
public class CrushingWall extends Solid
{

    private int distance;
    private int startY;
    private int moved;
    private int timer;
    private boolean descending;
    private GreenfootImage topImage;
    private GreenfootImage middleImage;
    private GreenfootImage bottomImage;

    // Cria a parede
    public CrushingWall(int distance) {
        super(Settings.CRUSH_SIZE, Settings.CRUSH_SIZE);
        moved = 0;
        timer = Settings.CRUSH_REST_TIME;
        descending = true;
        startY = 0;
        this.distance = Math.abs(distance) * Settings.TILE_SIZE;

        topImage = loadImage(Settings.CRUSH_TOP_IMAGE);
        middleImage = topImage;
        bottomImage = loadImage(Settings.CRUSH_BOTTOM_IMAGE);

        setImage(topImage);
    }
    //Carrega uma imagem e ajusta-a ao tamanho da parede
    private static GreenfootImage loadImage(String filename) {
        GreenfootImage img = new GreenfootImage(filename);
        img.scale(Settings.CRUSH_SIZE, Settings.CRUSH_SIZE);
        return img;
    }
    //Guarda a posição inicial quando a parede é adicionada ao mundo
    @Override
    public void addedToWorld(World world) {
        startY = getY();
    }
    //Mexe a parede, escolhe a imagem e fere os agentes que tocar
    @Override
    public void act() {
        moveWall();
        animate();
        damageTouchingAgents();
    }
    //Controla o ciclo: esperar, descer, esperar, subir
    private void moveWall() {
        if (timer > 0) {
            timer--;
            return;
        }

        if (descending) {
            moved += Math.min(Settings.CRUSH_DOWN_SPEED, distance - moved);

            if (moved >= distance) {
                descending = false;
                timer = Settings.CRUSH_WAIT_TIME;
            }
        }
        else {
            moved -= Math.min(Settings.CRUSH_UP_SPEED, moved);

            if (moved <= 0) {
                descending = true;
                timer = Settings.CRUSH_REST_TIME;
            }
        }

        setLocation(getX(), startY + moved);
    }
    //Escolhe a imagem conforme a posição (cima, meio ou baixo)
    private void animate() {
        if (moved <= 0) {
            setImage(topImage);
        }
        else if (moved >= distance) {
            setImage(bottomImage);
        }
        else {
            setImage(middleImage);
        }
    }
    //Tira uma vida aos agentes que estiverem a tocar na parede
    private void damageTouchingAgents() {
        for (Agent agent : getIntersectingObjects(Agent.class)) {
            agent.takeDamage();
        }
    }
}