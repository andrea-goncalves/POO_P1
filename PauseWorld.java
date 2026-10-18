import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** Pause screen: resume, save the progress, or go back to the main menu. */
public class PauseWorld extends ScreenWorld
{
    private Level pausedLevel;
    private int inputDelay = 20;      

    public PauseWorld(Level pausedLevel)
    {
        this.pausedLevel = pausedLevel;
        drawScreen("");
    }

    
    private void drawScreen(String message)
    {
        GreenfootImage bg = createBackground();

        drawCentered(bg, "PAUSED", 56, Color.WHITE, 90);
        drawCentered(bg, "R  -  resume/continuar", 28, Color.WHITE, 200);
        drawCentered(bg, "S  -  save game/guardar o jogo", 28, Color.WHITE, 250);
        drawCentered(bg, "M  -  menu", 28, Color.WHITE, 300);

        drawCentered(bg, "Guardar grava o inicio do nivel (nivel "
                         + pausedLevel.getLevelNumber() + ")", 18, Color.LIGHT_GRAY, 370);

        if (!message.isEmpty()) {
            drawCentered(bg, message, 26, Color.GREEN, 420);
        }
        setBackground(bg);
    }

    public void act()
    {
        if (inputDelay > 0) {
            inputDelay--;
            return;
        }
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(pausedLevel);                
        }
        else if (Greenfoot.isKeyDown("s")) {
            SaveGame.save(pausedLevel.getLevelNumber());
            drawScreen("Jogo Guardado");
        }
        else if (Greenfoot.isKeyDown("m")) {
            Greenfoot.setWorld(new MenuWorld());
        }
    }
}