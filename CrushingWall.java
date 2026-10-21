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
    private GreenfootImage topo;
    private GreenfootImage meio;
    private GreenfootImage baixo;

    public CrushingWall(int distance) {
        super(64, 64);
        moved = 0; 
        timer = REST_TIME; 
        descending = true; 
        startY = 0;
        this.distance = Math.abs(distance) * Level.TILE_SIZE;

        topo = loadImage("crushWallTopo.png");
        //meio = loadImage("crusher_meio.png");
        meio=topo;
        baixo = loadImage("crusherWall_down.png");

        setImage(topo);
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
        checkCollision();
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
            setImage(topo);
        }
        else if (moved >= distance) {
            setImage(baixo);
        }
        else {
            setImage(meio);
        }
    }

    private void checkCollision() {
        for (Character player : getIntersectingObjects(Character.class)) {
            player.takeDamage();
        }
    }
}
