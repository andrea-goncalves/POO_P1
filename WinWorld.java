import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class WinWorld extends ScreenWorld
{
    private int inputDelay;
    public WinWorld(int finalScore)
    {
        inputDelay = 60;

        GreenfootImage bg = createBackground();
        drawCentered(bg, "MISSÃO CUMPRIDA", 56, Color.GREEN, 120);
        drawCentered(bg, "Zanarkin foi salva!", 28, Color.WHITE, 210);
        drawCentered(bg, "Score final: " + finalScore, 28, Color.YELLOW, 260);
        drawCentered(bg, "SPACE  -  voltar ao menu", 24, Color.WHITE, 350);
        setBackground(bg);
    }
    public void act()
    {
        if (inputDelay > 0) {
            inputDelay--;
            return;
        }
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new MenuWorld());
        }
    }
}
