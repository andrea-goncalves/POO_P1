import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class StoryWorld extends ScreenWorld
{
    private static final int LINE_SPACING = 40;
    private static final double SCROLL_SPEED = 1.0;      
    private static final Color TEXT_COLOR = new Color(255, 215, 0);
    private GreenfootImage stars;
       
    private static final String[] STORY = {
        "ZANARKIN foi atacada",
        "",
        "O pais inimigo destroiu....",
        "",
        "Os agentes Zig e Zag sobreviveram",
        
        "",
        "Com a maquina do tempo",
        "voltaram um dia antes",
        "do ataque",
        "",
        "a sua missão: impedir o ataque",
        "encontrando pistas",
        "escondendo-se nas sombras",
        "e desativando a bomba",
        "",
    
    };

    private GreenfootImage text;
    private double offsetY;                
    private int inputDelay;

    public StoryWorld()
    {        
        inputDelay = 20;
        stars = createStars();
        text = createText();
        offsetY = getHeight();
        setBackground(new GreenfootImage(stars)); 
    }

    
    private GreenfootImage createStars()
    {
        GreenfootImage img = new GreenfootImage(getWidth(), getHeight());
        img.setColor(Color.BLACK);
        img.fill();
        img.setColor(Color.WHITE);
        for (int i = 0; i < 150; i++) {
            img.fillRect(Greenfoot.getRandomNumber(getWidth()),
                         Greenfoot.getRandomNumber(getHeight()), 2, 2);
        }
        return img;
    }

   
    private GreenfootImage createText()
    {
        GreenfootImage img = new GreenfootImage(getWidth(), STORY.length * LINE_SPACING);
        for (int i = 0; i < STORY.length; i++) {
            if (!STORY[i].isEmpty()) {
                int size = (i == 0) ? 40 : 26;          
                drawCentered(img, STORY[i], size, TEXT_COLOR, i * LINE_SPACING);
            }
        }
        return img;
    }

    public void act()
    {
        offsetY -= SCROLL_SPEED;
        
        
        GreenfootImage bg = getBackground();
        bg.drawImage(stars, 0, 0);
        bg.drawImage(text, 0, (int) offsetY);
        
        
        boolean finished = offsetY + text.getHeight() < 0;
        boolean skipped = inputDelay == 0 && Greenfoot.isKeyDown("space");
        if (inputDelay > 0) {
            inputDelay--;
        }

        if (finished || skipped) {
            Greenfoot.setWorld(new MenuWorld());
        }
    }
}