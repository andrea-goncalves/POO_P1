import greenfoot.*;

public class PauseWorld extends ScreenWorld
{
    private Level pausedLevel;
    private int inputDelay;

    public PauseWorld(Level pausedLevel)
    {
        inputDelay = Settings.PAUSE_INPUT_DELAY;
        this.pausedLevel = pausedLevel;
        drawScreen("");
    }

    @Override
    public void act()
    {
        if (inputDelay > 0) {
            inputDelay--;
            return;
        }
        if (Greenfoot.isKeyDown(Settings.KEY_CONTINUE)) {
            Greenfoot.setWorld(pausedLevel);
        }
        else if (Greenfoot.isKeyDown(Settings.KEY_SAVE)) {
            SaveGame.save(pausedLevel.getLevelNumber(), pausedLevel.getStartScore());
            drawScreen("Jogo Guardado");
        }
        else if (Greenfoot.isKeyDown(Settings.KEY_MENU)) {
            Greenfoot.setWorld(new MenuWorld());
        }
    }

    private void drawScreen(String message)
    {
        GreenfootImage bg = createBackground();

        drawCentered(bg, "PAUSA", Settings.SIZE_TITLE, Color.RED, 90);
        drawCentered(bg, "C  -  continuar", Settings.SIZE_LARGE, Color.YELLOW, 200);
        drawCentered(bg, "G  -  guardar", Settings.SIZE_LARGE, Color.YELLOW, 250);
        drawCentered(bg, "M  -  menu", Settings.SIZE_LARGE, Color.YELLOW, 300);

        if (!message.isEmpty()) {
            drawCentered(bg, message, Settings.SIZE_MESSAGE, Color.GREEN, 420);
        }
        setBackground(bg);
    }
}