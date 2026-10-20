import greenfoot.*;

public class Solid extends Actor
{
    public Solid() {
        this(Level.TILE_SIZE, Level.TILE_SIZE);
    }

    public Solid(int width, int height) {
        setImage (new GreenfootImage(width, height));
    }
}