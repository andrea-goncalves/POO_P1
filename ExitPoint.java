import greenfoot.*;
import java.util.List;
/**
 * Saída do nível, quando todos os agentes estão em cima do ExitPoint
 * o jogo passa para o nível seguinte.
 */
public class ExitPoint extends Actor
{
    //Verifica em cada frame se os agentes chegaram à saída
    @Override
    public void act()
    {
        checkExit();
    }
    //Se todos os agentes tocam na saída, avança de nível
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