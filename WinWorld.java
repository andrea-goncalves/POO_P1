import greenfoot.*;

public class WinWorld extends ScreenWorld
{
    private int inputDelay;

    public WinWorld(int finalScore)
    {
        inputDelay = Settings.WIN_INPUT_DELAY;

        GreenfootImage bg = createBackground();
        drawCentered(bg, "MISSÃO CUMPRIDA", Settings.SIZE_TITLE, Color.GREEN, 120);
        drawCentered(bg, "Zanarkin foi salva!", Settings.SIZE_LARGE, Color.WHITE, 210);
        drawCentered(bg, "Score final: " + finalScore, Settings.SIZE_LARGE, Color.YELLOW, 260);
        drawCentered(bg, "SPACE  -  voltar ao menu", Settings.SIZE_NORMAL, Color.WHITE, 350);
        setBackground(bg);
    }


    @Override
    public void act()
    {
        if (inputDelay > 0) {
            inputDelay--;
            return;
        }
        if (Greenfoot.isKeyDown(Settings.KEY_CONFIRM)) {
            Greenfoot.setWorld(new MenuWorld());
        }
    }
}
