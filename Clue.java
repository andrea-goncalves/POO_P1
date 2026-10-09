import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Clue extends Actor
{
    private int points;
       
    public Clue()
    {
        this(10);
    }
    public Clue(int points)
    {
        this.points = points;

        GreenfootImage img = new GreenfootImage(24, 24);
        img.setColor(Color.YELLOW);
        img.fillOval(0, 0, 24, 24);
        img.setColor(Color.ORANGE);
        img.drawOval(0, 0, 23, 23);
        setImage(img);
    }
    public void act()
    {
        if (isTouching(Agent.class)) {
            ((Level) getWorld()).addScore(points);
            getWorld().removeObject(this);
        }
    }
}
