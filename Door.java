
import greenfoot.*;

public class Door extends Actor implements Activatable{
    private boolean isOpen;
    private int openDurationFrames;
    private int timer;

    private Solid collision;

    public Door(double openTimeSeconds) {
        openDurationFrames = (int) (openTimeSeconds * 60);
        setImage("door.png");
        isOpen = false;
        timer = 0; 
        collision = null;
    }

    protected void addedToWorld(World world) {
        collision = new Solid(
            getImage().getWidth(),
            getImage().getHeight()
        );

        world.addObject(collision, getX(), getY());
    }
    @Override
    public void act() {
        if (isOpen && openDurationFrames > 0) {
            timer--;

            if (timer <= 0 && getIntersectingObjects(Character.class).isEmpty()) {
                closeDoor();
            }
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void openDoor() {
        if (!isOpen) {
            isOpen = true;

            if (openDurationFrames > 0) {
                timer = openDurationFrames;
            }

            getWorld().removeObject(collision);
            getImage().setTransparency(100);
        }
    }

    public void closeDoor() {
        if (isOpen) {
            isOpen = false;

            getWorld().addObject(collision, getX(), getY());
            getImage().setTransparency(255);
        }
    }
    
    @Override
    public void activate()
    {
        openDoor();
    }
}
