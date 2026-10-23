
import greenfoot.*;

public class Computer extends Actor
{
    private Door[] associatedDoors;
    private MovingPlatform[] associatedPlatforms;
    private boolean keyPressed;
    private Activatable[] targets;
    private boolean keyHeld;

    public Computer(Activatable... targets)
    {
        this.targets = targets;
        keyHeld = false;
        setImage("computer.png");
    }

    public void act()
    {
        //checkInteraction();
        boolean eDown = Greenfoot.isKeyDown("e");

        if (isTouching(Agent.class) && eDown && !keyHeld) {
            for (Activatable target : targets) {
                target.activate();
            }
        }
        keyHeld = eDown;
    }
    private void checkInteraction() {
        Agent character = (Agent) getOneIntersectingObject(Agent.class);

        boolean eKey = Greenfoot.isKeyDown("e");

        if (character != null && eKey && !keyPressed) {

            for (Door door : associatedDoors) {
                if (door != null) {
                    door.openDoor();
                }
            }

            for (MovingPlatform platform : associatedPlatforms) {
                if (platform != null) {
                    platform.activate();
                }
            }
        }

        keyPressed = eKey;
    }
}