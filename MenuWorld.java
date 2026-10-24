import greenfoot.*;

public class MenuWorld extends ScreenWorld
{
    // Variables
    private int inputDelay;
    private GreenfootSound music;
    private boolean musicStarted;
    private boolean musicPaused;

    // Constructor
    public MenuWorld()
    {
        inputDelay = Settings.MENU_INPUT_DELAY;
        musicStarted = false;
        musicPaused = false;
        music = new GreenfootSound(Settings.MUSIC_MENU);
        music.setVolume(Settings.MUSIC_VOLUME);

        GreenfootImage bg = createBackground();
        drawTitle(bg);
        drawControls(bg, 130, "ZIG", new String[] {
            "A / D  -  andar", "W  -  saltar", "S  -  poderes", "F  -  combate"
        });
        drawControls(bg, 560, "ZAG", new String[] {
            "Left / Right  -  andar", "Up  -  saltar", "Down  -  poderes", "0  -  combate"
        });
        drawHelp(bg);
        drawOptions(bg);
        setBackground(bg);
    }


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

    public void stopped()
    {
        if (music.isPlaying()) {
            music.pause();
            musicPaused = true;
        }
    }

    public void started()
    {
        if (musicPaused) {
            music.play();
            musicPaused = false;
        }
    }

    private void goTo(World next)
    {
        music.stop();
        Greenfoot.setWorld(next);
    }

    private void drawTitle(GreenfootImage bg)
    {
        drawCentered(bg, "AGENTES ZIG & ZAG", Settings.SIZE_MENU_TITLE, Color.RED, 25);
        drawCentered(bg, "Recolhe as pistas para impedir o ataque ao Zanarkin", Settings.SIZE_SMALL, Color.LIGHT_GRAY, 90);
    }

    private void drawControls(GreenfootImage bg, int x, String name, String[] lines)
    {
        drawText(bg, name, Settings.SIZE_SECTION, Color.RED, x, 150);
        for (int i = 0; i < lines.length; i++) {
            drawText(bg, lines[i], Settings.SIZE_SMALL, Color.WHITE, x, 200 + i * 30);
        }
    }

    private void drawHelp(GreenfootImage bg)
    {
        drawCentered(bg, "E  -  ativar portas e plataformas", Settings.SIZE_SMALL, Color.YELLOW, 350);
        drawCentered(bg, "P  -  pausa", Settings.SIZE_SMALL, Color.YELLOW, 385);
        drawCentered(bg, "Os dois agentes devem chegar à saída para passar o nível", Settings.SIZE_SMALL, Color.WHITE, 420);
        drawCentered(bg, "Clica duas vezes no salto e deixa pressionado para flutuar", Settings.SIZE_SMALL, Color.WHITE, 450);
    }

    private void drawOptions(GreenfootImage bg)
    {
        if (SaveGame.hasSave()) {
            drawCentered(bg, "SPACE  -  novo jogo      C  -  continuar (nível " + SaveGame.load()
                + ")      T  -  história", Settings.SIZE_NORMAL, Color.YELLOW, 470);
        } else {
            drawCentered(bg, "SPACE  -  novo jogo      T  -  história", Settings.SIZE_NORMAL, Color.YELLOW, 470);
        }
    }
}