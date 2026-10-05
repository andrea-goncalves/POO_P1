import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        addObject(new Agent("a", "d", "w", "zig"), 400, 300);
        addObject(new Ground(600, 40), 300, 380);
        //addObject(new Agent("left", "right", "up", "zag"), 460, 300);
    }
    
}
