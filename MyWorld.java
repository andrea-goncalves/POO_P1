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
        addObject(new Zig(), 400, 300);
        //addObject(new Zag(), 460, 300);
        addObject(new Ground(600, 40), 300, 380);
        addObject(new Dog(), 200, 330);
        addObject(new Wall(20, 100), 100, 320);
      addObject(new Wall(20, 100), 500, 320);
    }
    
}
