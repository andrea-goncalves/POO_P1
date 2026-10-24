import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


    public abstract class ScreenWorld extends World
{
    public ScreenWorld()
    {
        super(960, 540, 1);
    }

    public GreenfootImage createBackground()
    {
        GreenfootImage img = new GreenfootImage(getWidth(), getHeight());
        img.setColor(new Color(20, 20, 30));
        img.fill();
        return img;
    }

    public void drawText(GreenfootImage target, String text, int size, Color color, int x, int y)
    {
        GreenfootImage line = new GreenfootImage(text, size, color, new Color(0, 0, 0, 0));
        target.drawImage(line, x, y);
    }

    public void drawCentered(GreenfootImage target, String text, int size, Color color, int y)
    {
        GreenfootImage line = new GreenfootImage(text, size, color, new Color(0, 0, 0, 0));
        target.drawImage(line, (target.getWidth() - line.getWidth()) / 2, y);
    }
}

