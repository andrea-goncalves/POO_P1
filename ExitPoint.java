import greenfoot.*;
import java.util.List;

public class ExitPoint extends Actor
{
    public void act()
    {
        checkExit();
    }

    private void checkExit()
    {
        List<Agent> players = getWorld().getObjects(Agent.class);

        if (players.size() != 2) return;

        if (intersects(players.get(0)) &&
            intersects(players.get(1))) {

            Level level = (Level) getWorld();
            level.advanceLevel();
        }
    }
}