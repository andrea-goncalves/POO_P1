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
        this.points = points;

        GreenfootImage img = new GreenfootImage(Settings.CLUE_SIZE, Settings.CLUE_SIZE);
        img.setColor(Settings.CLUE_FILL_COLOR);
        img.fillOval(0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE);
        img.setColor(Settings.CLUE_BORDER_COLOR);
        img.drawOval(0, 0, Settings.CLUE_SIZE - 1, Settings.CLUE_SIZE - 1);
        setImage(img);
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