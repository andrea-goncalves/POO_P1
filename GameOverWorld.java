import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOverWorld extends ScreenWorld
{
    private Level failedLevel;
    private int inputDelay;     

    public GameOverWorld(Level failedLevel)
    {
        inputDelay = 40;
        this.failedLevel = failedLevel;

        GreenfootImage bg = createBackground();

        drawCentered(bg, "GAME OVER", 56, Color.RED, 120);
        drawCentered(bg, "Um agente morreu", 24, Color.LIGHT_GRAY, 200);
        drawCentered(bg, "Score: " + failedLevel.getScore(), 24, Color.WHITE, 240);

        drawCentered(bg, "R  -  recomeçar o nível", 28, Color.YELLOW, 320);
        drawCentered(bg, "I  -  recomeçar do início", 28, Color.YELLOW, 370);

        setBackground(bg);
    }
    @Override
    public void act()
    {
        if (inputDelay > 0) {
            inputDelay--;
            return;
        }
        if (Greenfoot.isKeyDown("r")) {
            Level retry = failedLevel.createNew();
            retry.setStartingScore(failedLevel.getStartScore());
            Greenfoot.setWorld(retry);   
        }
        else if (Greenfoot.isKeyDown("i")) {
            Greenfoot.setWorld(new Level1());
        }
    }
}