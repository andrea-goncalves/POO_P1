import greenfoot.*;

public class DarknessOverlay extends Actor
{
    
    public DarknessOverlay(int width, int height)
    {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(Settings.DARKNESS_COLOR);
        img.fill();
        setImage(img);
    }
}
