import greenfoot.*;

public class MovingPlatform extends Solid
{
    private static final int SPEED = 2;
    private static final int WAIT_TIME = 180;

    private int moveX, moveY;
    private int startX, startY;
    private int timer;
    private boolean moving;
    private boolean returning;

    public MovingPlatform(int moveX, int moveY) {
        super();
        startX = 0; 
        startY = 0; 
        timer = 0; 
        moving = false; 
        returning = false;
        this.moveX = moveX;
        this.moveY = moveY;
        setImage("plataforma.png");
    }

    protected void addedToWorld(World world) {
        startX = getX();
        startY = getY();
    }

    public void act() {
        movePlatform();
    }

    public void activate() {
        if (!moving && (moveX != 0 || moveY != 0)) {
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

        int targetX = startX + moveX;
        int targetY = startY + moveY;

        if (!returning) {
            if (getX() != targetX) {
                moveTowards(targetX, getY());
            }
            else if (getY() != targetY) {
                moveTowards(getX(), targetY);
            }
            else {
                returning = true;
                timer = WAIT_TIME;
            }

        } else {
            if (getY() != startY) {
                moveTowards(getX(), startY);
            }
            else if (getX() != startX) {
                moveTowards(startX, getY());
            }
            else {
                moving = false;
                returning = false;
            }
        }
    }

    private void moveTowards(int targetX, int targetY) {
        int dx = targetX - getX();
        int dy = targetY - getY();

        int stepX = Math.max(-SPEED, Math.min(SPEED, dx));
        int stepY = Math.max(-SPEED, Math.min(SPEED, dy));

        setLocation(getX() + stepX, getY() + stepY);
    }
}
