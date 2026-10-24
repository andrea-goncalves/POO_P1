import greenfoot.*;

public class Clue extends Actor
{
    private int points;

    public Clue()
    {
        this(Settings.CLUE_DEFAULT_POINTS);
    }

    public Clue(int points)
    {
        this.points = points;

        GreenfootImage img = new GreenfootImage(Settings.CLUE_SIZE, Settings.CLUE_SIZE);
        img.setColor(Settings.CLUE_FILL_COLOR);
        img.fillOval(0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE);
        img.setColor(Settings.CLUE_BORDER_COLOR);
        img.drawOval(0, 0, Settings.CLUE_SIZE - 1, Settings.CLUE_SIZE - 1);
        setImage(img);
    }

    @Override
    public void act()
    {
        if (isTouching(Agent.class)) {
            ((Level) getWorld()).addScore(points);
            getWorld().removeObject(this);
        }
    }
}