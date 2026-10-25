import greenfoot.*;
/**
 * Ecrã de pausa: permite continuar o nível, guardar o jogo ou voltar ao menu.
 */
public class PauseWorld extends ScreenWorld
{
    private Level pausedLevel;
    private int inputDelay;

    //Cria o ecrã de pausa para o nível indicado
    public PauseWorld(Level pausedLevel)
    {
        inputDelay = Settings.PAUSE_INPUT_DELAY;
        this.pausedLevel = pausedLevel;
        drawScreen("");
    }

    //Trata das teclas: continuar (C), guardar (G) ou menu (M).
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

    //Desenha o ecrã
    private void drawScreen(String message)
    {
        GreenfootImage bg = createBackground();

        drawCentered(bg, "PAUSA", Settings.SIZE_TITLE, Color.RED, Settings.PAUSE_TITLE_Y);
        drawCentered(bg, "C  -  continuar", Settings.SIZE_LARGE, Color.YELLOW, Settings.PAUSE_CONTINUE_Y);
        drawCentered(bg, "G  -  guardar", Settings.SIZE_LARGE, Color.YELLOW, Settings.PAUSE_SAVE_Y);
        drawCentered(bg, "M  -  menu", Settings.SIZE_LARGE, Color.YELLOW, Settings.PAUSE_MENU_Y);

        if (!message.isEmpty()) {
            drawCentered(bg, message, Settings.SIZE_MESSAGE, Color.GREEN, Settings.PAUSE_MESSAGE_Y);
        }
        setBackground(bg);
    }
}