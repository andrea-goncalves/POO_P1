
import greenfoot.*;

public class Computer extends Actor
{
    private Activatable[] targets;
    private boolean keyHeld;

    public Computer(Activatable... targets)
    {
        this.targets = targets;
        keyHeld = false;
        setImage(Settings.COMPUTER_IMAGE);
    }

    @Override
    public void act()
    {
        boolean interactDown = Greenfoot.isKeyDown(Settings.KEY_INTERACT);

        if (isTouching(Agent.class) && interactDown && !keyHeld) {
            for (Activatable target : targets) {
                target.activate();
            }
        }
        keyHeld = interactDown;
    }
}