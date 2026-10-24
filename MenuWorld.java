import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MenuWorld extends ScreenWorld
{
    private int inputDelay;     
    private GreenfootSound music;
    private boolean musicStarted;
    private boolean musicPaused;
    public MenuWorld()
    {
        GreenfootImage bg = createBackground();
        inputDelay = 20;
        musicStarted = false;
        musicPaused = false; 
        music = new GreenfootSound("spy.wav");
        music.setVolume(50);
        

        drawCentered(bg, "AGENTES ZIG & ZAG", 48, Color.RED, 25);
        drawCentered(bg, "Recolhe as pistas para impedir o ataque ao Zanarkin", 20, Color.LIGHT_GRAY, 90);

        // Zig 
        drawText(bg, "ZIG", 30, Color.RED, 130, 150);
        drawText(bg, "A / D  -  andar", 20, Color.WHITE, 130, 200);
        drawText(bg, "W  -  saltar", 20, Color.WHITE, 130, 230);
        drawText(bg, "S  -  poderes", 20, Color.WHITE, 130, 260);
        drawText(bg, "F  -  combate ", 20, Color.WHITE, 130, 290);

        // Zag 
        drawText(bg, "ZAG", 30, Color.RED, 560, 150);
        drawText(bg, "Left / Right  -  andar", 20, Color.WHITE, 560, 200);
        drawText(bg, "Up  -  saltar", 20, Color.WHITE, 560, 230);
        drawText(bg, "Down  -  poderes", 20, Color.WHITE, 560, 260);
        drawText(bg, "0  -  combate", 20, Color.WHITE, 560, 290);

        // Shared 
        drawCentered(bg, "E  -  ativar portas e plataformas", 20, Color.YELLOW, 350);
        drawCentered(bg, "Clica duas vezes no salto e deixa pressionado para flutuar", 20, Color.WHITE, 385);
        drawCentered(bg, "Os dois agentes devem chegar à saída para passar o nível", 20, Color.WHITE, 420);
        drawCentered(bg, "P  -  pausa (continuar, guardar ou voltar ao menu)", 20, Color.YELLOW, 450);

        if (SaveGame.hasSave()) {
            drawCentered(bg, "SPACE  -  novo jogo      C  -  continar (nivel "+ SaveGame.load() + ")      T  -  historia", 24, Color.YELLOW, 470);
        } else {
            drawCentered(bg, "SPACE  -  novo jogo      T  -  historia", 24, Color.YELLOW, 470);
        }

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
        if (Greenfoot.isKeyDown("space")) {
            goTo(new Level1());
        }
        else if (Greenfoot.isKeyDown("c") && SaveGame.hasSave()) {
            Level saved = SaveGame.createLevel(SaveGame.load());
            saved.setStartingScore(SaveGame.loadScore());
            goTo(saved);
        }
        else if (Greenfoot.isKeyDown("t")) {
            goTo(new StoryWorld());
        }
    }

    private void goTo(World next)
    {
        music.stop();
        Greenfoot.setWorld(next);
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
}