import greenfoot.*;
public class Frames  
{
    public static GreenfootImage[] load(String prefix, int count)
    {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++) {
            frames[i] = new GreenfootImage(prefix + (i + 1) + ".png");
        }
        return frames;
    }

    public static GreenfootImage[] mirror(GreenfootImage[] frames)
    {
        GreenfootImage[] flipped = new GreenfootImage[frames.length];
        for (int i = 0; i < frames.length; i++) {
            flipped[i] = new GreenfootImage(frames[i]);
            flipped[i].mirrorHorizontally();
        }
        return flipped;
    }
}
