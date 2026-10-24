import greenfoot.*;

public abstract class ScreenWorld extends World
{
    public ScreenWorld()
    {
        super(Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT, Settings.CELL_SIZE);
    }

    public GreenfootImage createBackground()
    {
        GreenfootImage img = new GreenfootImage(getWidth(), getHeight());
        img.setColor(Settings.SCREEN_BACKGROUND);
        img.fill();
        return img;
    }

    public void drawText(GreenfootImage target, String text, int size, Color color, int x, int y)
    {
        GreenfootImage line = new GreenfootImage(text, size, color, Settings.TRANSPARENT);
        target.drawImage(line, x, y);
    }

    public void drawCentered(GreenfootImage target, String text, int size, Color color, int y)
    {
        GreenfootImage line = new GreenfootImage(text, size, color, Settings.TRANSPARENT);
        target.drawImage(line, (target.getWidth() - line.getWidth()) / 2, y);
    }
}

