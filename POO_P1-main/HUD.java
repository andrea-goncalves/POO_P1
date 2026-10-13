import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class HUD extends Actor
{
            
    private static final Font FONT = new Font("Arial", true, false, 18);

    public HUD(int width)
    {
        setImage(new GreenfootImage(width, 30));
    }
    public void act()
    {
        Level level = (Level) getWorld();
        
        String text = "";
        for (Agent agent : getWorld().getObjects(Agent.class)) {
            text += "Lives: " + agent.getLives() + "     ";
        }
        text += "Score: " + ((Level) getWorld()).getScore() + "     ";

        if (level.isPowerUnlocked()) {
            text += level.getPowerName() + ": READY";
        } else {
            text += level.getPowerName() + ": " + level.getScore() + "/" + level.getUnlockScore();
        }
        
        GreenfootImage img = getImage();
        img.clear();                      
        img.setColor(Color.WHITE);
        img.setFont(FONT);
        img.drawString(text, 10, 22);
    }
}
