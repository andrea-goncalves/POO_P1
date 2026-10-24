import greenfoot.*;
import java.util.List;

public class ExitPoint extends Actor
{
    @Override
    public void act()
    {
        checkExit();
    }

    private void checkExit()
    {
        List<Agent> agents = getWorld().getObjects(Agent.class);
        if (agents.isEmpty()) return;

        for (Agent agent : agents) {
            if (!intersects(agent)) return;      
        }
        ((Level) getWorld()).advanceLevel();
    }
}