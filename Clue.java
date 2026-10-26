import greenfoot.*;
/**
 * Pista que os agentes podem apanhar. Ao ser tocada soma pontos
 * à pontuação do nível e desaparece.
 */
public class Clue extends Actor
{   
    private int points;
    //Pista com o valor padrão
    public Clue()
    {
        this(Settings.CLUE_DEFAULT_POINTS);
        
    }
    //Pista com um valor à escolha; desenha um círculo amarelo com contorno
    public Clue(int points)
    {
        setImage(Settings.CLUE_IMAGE);
        this.points = points;
    }
    //Se um agente tocar na pista, soma os pontos e remove a pista
    @Override
    public void act()
    {
        if (isTouching(Agent.class)) {
            ((Level) getWorld()).addScore(points);
            getWorld().removeObject(this);
        }
    }
}