import greenfoot.*;

public class Solid extends Actor
{
    public Solid() {
        this(Settings.TILE_SIZE, Settings.TILE_SIZE);
    }

    public Solid(int width, int height) {
        setImage (new GreenfootImage(width, height));
    }
}