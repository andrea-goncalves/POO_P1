import greenfoot.*;

public class GameOverWorld extends ScreenWorld
{
    private Level failedLevel;
    private int inputDelay;

    public GameOverWorld(Level failedLevel)
    {
        inputDelay = Settings.GAME_OVER_INPUT_DELAY;
        this.failedLevel = failedLevel;

        GreenfootImage bg = createBackground();

        drawCentered(bg, "GAME OVER", Settings.SIZE_TITLE, Color.RED, 120);
        drawCentered(bg, "Um agente morreu", Settings.SIZE_NORMAL, Color.LIGHT_GRAY, 200);
        drawCentered(bg, "Score: " + failedLevel.getScore(), Settings.SIZE_NORMAL, Color.WHITE, 240);

        drawCentered(bg, "R  -  recomeçar o nível", Settings.SIZE_LARGE, Color.YELLOW, 320);
        drawCentered(bg, "I  -  recomeçar do início", Settings.SIZE_LARGE, Color.YELLOW, 370);

        setBackground(bg);
    }

    @Override
    public void act()
    {
        if (inputDelay > 0) {
            inputDelay--;
            return;
        }
        if (Greenfoot.isKeyDown(Settings.KEY_RETRY)) {
            Level retry = failedLevel.createNew();
            retry.setStartingScore(failedLevel.getStartScore());
            Greenfoot.setWorld(retry);
        }
        else if (Greenfoot.isKeyDown(Settings.KEY_RESTART)) {
            Greenfoot.setWorld(new Level1());
        }
    }
}