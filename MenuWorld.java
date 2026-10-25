import greenfoot.*;
/**
 * Ecrã inicial: mostra os controlos e permite começar um jogo novo,
 * continuar um jogo guardado ou ver a história. Toca a música do menu.
 */
public class MenuWorld extends ScreenWorld
{
    private int inputDelay;
    private GreenfootSound music;
    private boolean musicStarted;
    private boolean musicPaused;

    public MenuWorld()
    {
        inputDelay = Settings.MENU_INPUT_DELAY;
        musicStarted = false;
        musicPaused = false;
        music = new GreenfootSound(Settings.MUSIC_MENU);
        music.setVolume(Settings.MUSIC_VOLUME);

        GreenfootImage bg = createBackground();
        drawTitle(bg);
        drawControls(bg, Settings.MENU_ZIG_X, "ZIG", new String[] {
            "A / D  -  andar", "W  -  saltar", "S  -  poderes", "F  -  combate"
        });
        drawControls(bg, Settings.MENU_ZAG_X, "ZAG", new String[] {
            "Left / Right  -  andar", "Up  -  saltar", "Down  -  poderes", "0  -  combate"
        });
        drawHelp(bg);
        drawOptions(bg);
        setBackground(bg);
    }
    //Inicia a música e trata das teclas: novo jogo, continuar ou história.
    @Override
    public void act()
    {
        if (!musicStarted) {
            music.play();
            musicStarted = true;
        }
        if (inputDelay > 0) {
            inputDelay--;
            return;
        }
        if (Greenfoot.isKeyDown(Settings.KEY_CONFIRM)) {
            goTo(new Level1());
        }
        else if (Greenfoot.isKeyDown(Settings.KEY_CONTINUE) && SaveGame.hasSave()) {
            Level saved = SaveGame.createLevel(SaveGame.load());
            saved.setStartingScore(SaveGame.loadScore());
            goTo(saved);
        }
        else if (Greenfoot.isKeyDown(Settings.KEY_STORY)) {
            goTo(new StoryWorld());
        }
    }
    //Chamado pelo Greenfoot quando se carrega em Pause: pausa a música.
    public void stopped()
    {
        if (music.isPlaying()) {
            music.pause();
            musicPaused = true;
        }
    }
    //Chamado pelo Greenfoot quando se carrega em Run: retoma a música.
    public void started()
    {
        if (musicPaused) {
            music.play();
            musicPaused = false;
        }
    }
    //Pára a música e muda para outro mundo.
    private void goTo(World next)
    {
        music.stop();
        Greenfoot.setWorld(next);
    }
    //Desenha o título e o subtítulo.
    private void drawTitle(GreenfootImage bg)
    {
        drawCentered(bg, "AGENTES ZIG & ZAG", Settings.SIZE_MENU_TITLE, Color.RED, Settings.MENU_TITLE_Y);
        drawCentered(bg, "Recolhe as pistas para impedir o ataque ao Zanarkin",
            Settings.SIZE_SMALL, Color.LIGHT_GRAY, Settings.MENU_SUBTITLE_Y);
    }
    //Desenha a coluna de controlos de um agente.
    private void drawControls(GreenfootImage bg, int x, String name, String[] lines)
    {
        drawText(bg, name, Settings.SIZE_SECTION, Color.RED, x, Settings.MENU_HEADER_Y);
        for (int i = 0; i < lines.length; i++) {
            drawText(bg, lines[i], Settings.SIZE_SMALL, Color.WHITE, x,
                Settings.MENU_FIRST_LINE_Y + i * Settings.MENU_LINE_SPACING);
        }
    }
    //Desenha as dicas gerais do jogo
    private void drawHelp(GreenfootImage bg)
    {
        drawCentered(bg, "E  -  ativar portas e plataformas", Settings.SIZE_SMALL, Color.YELLOW, Settings.MENU_HELP_INTERACT_Y);
        drawCentered(bg, "P  -  pausa", Settings.SIZE_SMALL, Color.YELLOW, Settings.MENU_HELP_PAUSE_Y);
        drawCentered(bg, "Os dois agentes devem chegar à saída para passar o nível", Settings.SIZE_SMALL, Color.WHITE, Settings.MENU_HELP_EXIT_Y);
        drawCentered(bg, "Clica duas vezes no salto e deixa pressionado para flutuar", Settings.SIZE_SMALL, Color.WHITE, Settings.MENU_HELP_GLIDE_Y);
    }
    //Desenha as opções; só mostra "continuar" se existir um jogo guardado.
    private void drawOptions(GreenfootImage bg)
    {
        if (SaveGame.hasSave()) {
            drawCentered(bg, "SPACE  -  novo jogo      C  -  continuar (nível " + SaveGame.load()
                + ")      T  -  história", Settings.SIZE_NORMAL, Color.YELLOW, Settings.MENU_OPTIONS_Y);
        } else {
            drawCentered(bg, "SPACE  -  novo jogo      T  -  história", Settings.SIZE_NORMAL, Color.YELLOW, Settings.MENU_OPTIONS_Y);
        }
    }
}