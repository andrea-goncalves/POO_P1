import greenfoot.*;

public class MovingPlatform extends Solid implements Activatable
{
    private static final int SPEED = 2;
    private static final int WAIT_TIME = 180;

    private int moveY;
    private int startY;
    private int timer;
    private boolean moving;
    private boolean returning;

    public MovingPlatform( int moveY) {
        super();
        this.moveY = moveY;
        startY = 0; 
        timer = 0; 
        moving = false; 
        returning = false;
        
        setImage("plataforma.png");
    }

    protected void addedToWorld(World world) {
        startY = getY();
    }
    @Override
    public void act() {
        movePlatform();
    }

    @Override
    public void activate() {
        if (!moving && moveY != 0) {
            moving = true;
            returning = false;
            timer = 0;
        }
    }

    private void movePlatform() {
        if (!moving) return;

        if (timer > 0) {
            timer--;
            return;
        }

        int targetY = returning ? startY : startY + moveY;

        if (getY() != targetY) {
            int step = Math.max(-SPEED, Math.min(SPEED, targetY - getY()));
            setLocation(getX(), getY() + step);
        }
        else if (returning) {
            moving = false;
            returning = false;
        }
        else {
            returning = true;
            timer = WAIT_TIME;
        }
    }
}


