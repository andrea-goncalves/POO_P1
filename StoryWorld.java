import greenfoot.*;

public class StoryWorld extends ScreenWorld
{

    private static final String[] STORY = {
            "ZANARKIN foi Destruída",
            "",
            "O país inimigo, Midgar, lançou uma bomba",
            "",
            "que acabou com metade da população",
            "",
            "Os agentes Zig e Zag devem",
            "voltar aos dias antes do ataque,",
            "com a ajuda da máquina do tempo",
            "",
            "para impedir o lançamento da bomba,",
            "encontrando pistas,",
            "escondendo-se nas sombras",
            "e acabando com a ameaça",
            ""
        };

    private GreenfootImage stars;
    private GreenfootImage text;
    private double offsetY;
    private int inputDelay;

    public StoryWorld()
    {
        inputDelay = Settings.STORY_INPUT_DELAY;
        stars = createStars();
        text = createText();
        offsetY = getHeight();
        setBackground(new GreenfootImage(stars));
    }

    @Override
    public void act()
    {
        offsetY -= Settings.STORY_SCROLL_SPEED;

        GreenfootImage bg = getBackground();
        bg.drawImage(stars, 0, 0);
        bg.drawImage(text, 0, (int) offsetY);

        boolean finished = offsetY + text.getHeight() < 0;
        boolean skipped = inputDelay == 0 && Greenfoot.isKeyDown(Settings.KEY_CONFIRM);
        if (inputDelay > 0) {
            inputDelay--;
        }

        if (finished || skipped) {
            Greenfoot.setWorld(new MenuWorld());
        }
    }

    private GreenfootImage createStars()
    {
        GreenfootImage img = new GreenfootImage(getWidth(), getHeight());
        img.setColor(Color.BLACK);
        img.fill();
        img.setColor(Color.WHITE);
        for (int i = 0; i < Settings.STORY_STAR_COUNT; i++) {
            img.fillRect(Greenfoot.getRandomNumber(getWidth()),
                Greenfoot.getRandomNumber(getHeight()),
                Settings.STORY_STAR_SIZE, Settings.STORY_STAR_SIZE);
        }
        return img;
    }

    private GreenfootImage createText()
    {
        GreenfootImage img = new GreenfootImage(getWidth(), STORY.length * Settings.STORY_LINE_SPACING);
        for (int i = 0; i < STORY.length; i++) {
            if (!STORY[i].isEmpty()) {
                int size = (i == 0) ? Settings.STORY_TITLE_SIZE : Settings.STORY_TEXT_SIZE;
                drawCentered(img, STORY[i], size, Settings.STORY_TEXT_COLOR, i * Settings.STORY_LINE_SPACING);
            }
        }
        return img;
    }
}