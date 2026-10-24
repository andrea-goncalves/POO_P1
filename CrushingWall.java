import greenfoot.*;

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

    private static GreenfootImage loadImage(String filename) {
        GreenfootImage img = new GreenfootImage(filename);
        img.scale(Settings.CRUSH_SIZE, Settings.CRUSH_SIZE);
        return img;
    }

    @Override
    public void addedToWorld(World world) {
        startY = getY();
    }

    @Override
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