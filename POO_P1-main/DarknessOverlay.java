import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class DarknessOverlay extends Actor
{
    public DarknessOverlay(int width, int height)
    {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(new Color(0, 0, 0, 150));    
        img.fill();
        setImage(img);
    }
    

}
