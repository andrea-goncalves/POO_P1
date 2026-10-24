import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class PauseWorld extends ScreenWorld
{
    private Level pausedLevel;
    private int inputDelay;      

    public PauseWorld(Level pausedLevel)
    {
        inputDelay = 20;
        this.pausedLevel = pausedLevel;
        drawScreen("");
    }

    
    private void drawScreen(String message)
    {
        GreenfootImage bg = createBackground();

        drawCentered(bg, "PAUSA", 56, Color.RED, 90);
        drawCentered(bg, "C  -  continuar", 28, Color.YELLOW, 200);
        drawCentered(bg, "G  -  guardar ", 28, Color.YELLOW, 250);
        drawCentered(bg, "M  -  menu", 28, Color.YELLOW, 300);

        if (!message.isEmpty()) {
            drawCentered(bg, message, 26, Color.GREEN, 420);
        }
        setBackground(bg);
    }
    @Override
    public void act()
    {
        if (inputDelay > 0) {
            inputDelay--;
            return;
        }
        if (Greenfoot.isKeyDown("c")) {
            Greenfoot.setWorld(pausedLevel);                
        }
        else if (Greenfoot.isKeyDown("g")) {
            SaveGame.save(pausedLevel.getLevelNumber(), pausedLevel.getStartScore());
            drawScreen("Jogo Guardado");
        }
        else if (Greenfoot.isKeyDown("m")) {
            Greenfoot.setWorld(new MenuWorld());
        }
    }
}