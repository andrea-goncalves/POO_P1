import greenfoot.*;

public class HUD extends Actor
{
   
    private static final Font FONT = new Font(Settings.HUD_FONT_NAME, Settings.HUD_FONT_BOLD,
        Settings.HUD_FONT_ITALIC, Settings.HUD_FONT_SIZE);

   
    public HUD(int width)
    {
        setImage(new GreenfootImage(width, Settings.HUD_HEIGHT));
    }

    
    @Override
    public void act()
    {
        Level level = (Level) getWorld();

        String text = "";
        for (Agent agent : level.getObjects(Agent.class)) {
            text += Settings.HUD_LIVES_LABEL + agent.getLives() + Settings.HUD_SEPARATOR;
        }
        text += Settings.HUD_SCORE_LABEL + level.getScore() + Settings.HUD_SEPARATOR;
        text += powerText(level);

        GreenfootImage img = getImage();
        img.clear();
        img.setColor(Color.WHITE);
        img.setFont(FONT);
        img.drawString(text, Settings.HUD_TEXT_X, Settings.HUD_TEXT_Y);
    }

    
    private String powerText(Level level)
    {
        String name = level.getPowerName() + Settings.HUD_POWER_SEPARATOR;

        if (level.isPowerUnlocked()) {
            return name + Settings.HUD_READY;
        }
        return name + level.getScore() + Settings.HUD_PROGRESS_SEPARATOR + level.getUnlockScore();
    }
}
