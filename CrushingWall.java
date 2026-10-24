import greenfoot.*;

public class CrushingWall extends Solid
{
    private static final int DOWN_SPEED = 8;
    private static final int UP_SPEED = 2;
    private static final int WAIT_TIME = 60;
    private static final int REST_TIME = 120;

    private int distance;
    private int startY;
    private int moved;
    private int timer;
    private boolean descending;
    private GreenfootImage topImage;
    private GreenfootImage middleImage;
    private GreenfootImage bottomImage;

    public CrushingWall(int distance) {
        super(64, 64);
        moved = 0; 
        timer = REST_TIME; 
        descending = true; 
        startY = 0;
        this.distance = Math.abs(distance) * Level.TILE_SIZE;

        topImage = loadImage("crushWallTopo.png");
        middleImage=topImage;
        bottomImage = loadImage("crusherWall_down.png");

        setImage(topImage);
    }

    private GreenfootImage loadImage(String filename) {
        GreenfootImage img = new GreenfootImage(filename);
        img.scale(64, 64);
        return img;
    }

    protected void addedToWorld(World world) {
        startY = getY();
    }

    public void act() {
        moveWall();
        animate();
        damageTouchingAgents();
    }

    private void moveWall() {
        if (timer > 0) {
            timer--;
            return;
        }

        if (descending) {
            moved += Math.min(DOWN_SPEED, distance - moved);

            if (moved >= distance) {
                descending = false;
                timer = WAIT_TIME;
            }
        }
        else {
            moved -= Math.min(UP_SPEED, moved);

            if (moved <= 0) {
                descending = true;
                timer = REST_TIME;
            }
        }

        setLocation(getX(), startY + moved);
    }

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

    private void damageTouchingAgents() {
        for (Agent agent : getIntersectingObjects(Agent.class)) {
            agent.takeDamage();
        }
    }
}
