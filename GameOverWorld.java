import greenfoot.*;
/**
 * Ecrã de fim de jogo: aparece quando um agente fica sem vidas e
 * permite repetir o nível ou recomeçar do início.
 */
public class GameOverWorld extends ScreenWorld
{
    private Level failedLevel;
    private int inputDelay;
    //Desenha o ecrã com a pontuação e as opções
    public GameOverWorld(Level failedLevel)
    {
        inputDelay = Settings.GAME_OVER_INPUT_DELAY;
        this.failedLevel = failedLevel;

        GreenfootImage bg = createBackground();

        drawCentered(bg, "GAME OVER", Settings.SIZE_TITLE, Color.RED, Settings.GAME_OVER_TITLE_Y);
        drawCentered(bg, "Um agente morreu", Settings.SIZE_NORMAL, Color.LIGHT_GRAY, Settings.GAME_OVER_TEXT_Y);
        drawCentered(bg, "Score: " + failedLevel.getScore(), Settings.SIZE_NORMAL, Color.WHITE, Settings.GAME_OVER_SCORE_Y);

        drawCentered(bg, "R  -  recomeçar o nível", Settings.SIZE_LARGE, Color.YELLOW, Settings.GAME_OVER_RETRY_Y);
        drawCentered(bg, "I  -  recomeçar do início", Settings.SIZE_LARGE, Color.YELLOW, Settings.GAME_OVER_RESTART_Y);

        setBackground(bg);
    }
    //Espera a escolha do jogador: repetir o nível (R) ou recomeçar no nível 1 (I)
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