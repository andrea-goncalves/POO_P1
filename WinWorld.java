import greenfoot.*;

//Ecrã de vitória, mostra a pontuação final depois de derrotar o bosse permite voltar ao menu.

public class WinWorld extends ScreenWorld
{
    private int inputDelay;
    //mostra a mensagem de vitória e a pontuação final
    public WinWorld(int finalScore)
    {
        inputDelay = Settings.WIN_INPUT_DELAY;

        GreenfootImage bg = createBackground();
        drawCentered(bg, "MISSÃO CUMPRIDA", Settings.SIZE_TITLE, Color.GREEN, Settings.WIN_TITLE_Y);
        drawCentered(bg, "Zanarkin foi salva!", Settings.SIZE_LARGE, Color.WHITE, Settings.WIN_TEXT_Y);
        drawCentered(bg, "Score final: " + finalScore, Settings.SIZE_LARGE, Color.YELLOW, Settings.WIN_SCORE_Y);
        drawCentered(bg, "SPACE  -  voltar ao menu", Settings.SIZE_NORMAL, Color.WHITE, Settings.WIN_HINT_Y);
        setBackground(bg);
    }
    //Volta ao menu quando o jogador carrega na tecla de confirmar
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
