
import greenfoot.*;

public class Computer extends Actor
{
    private Activatable[] targets;
    private boolean keyHeld;

    public Computer(Activatable... targets)
    {
        this.targets = targets;
        keyHeld = false;
        setImage("computer.png");
    }
    @Override
    public void act()
    {
        boolean eDown = Greenfoot.isKeyDown("e");

        if (isTouching(Agent.class) && eDown && !keyHeld) {
            for (Activatable target : targets) {
                target.activate();
            }
        }
        keyHeld = eDown;
    }

}