import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MenuWorld extends ScreenWorld
{
    private int inputDelay = 20;      

    public MenuWorld()
    {
        GreenfootImage bg = createBackground();

        drawCentered(bg, "AGENTES ZIG & ZAG", 48, Color.RED, 25);
        drawCentered(bg, "Recolhe as pistas para imperdir o ataque ao Zanarkin", 20, Color.LIGHT_GRAY, 90);

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
        drawCentered(bg, "P  -  pausa (continuar, guardar or voltar ao menu)", 20, Color.YELLOW, 450);
        
        
        if (SaveGame.hasSave()) {
            drawCentered(bg, "SPACE  -  novo jogo      C  -  continar (nivel "
                             + SaveGame.load() + ")      T  -  historia", 24, Color.YELLOW, 470);
        } else {
            drawCentered(bg, "SPACE  -  novo jogo      T  -  historia", 24, Color.YELLOW, 470);
        }
        
        setBackground(bg);
    }

    public void act()
    {
        if (inputDelay > 0) {
            inputDelay--;
            return;
        }
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new Level1());
        }
        else if (Greenfoot.isKeyDown("c") && SaveGame.hasSave()) {
            Greenfoot.setWorld(SaveGame.createLevel(SaveGame.load()));
        }
        else if (Greenfoot.isKeyDown("t")) {
            Greenfoot.setWorld(new StoryWorld());
        }
    }
}