import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Level1 extends Level
{
    public Level1()
    {
        super(960, 540);

        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(Color.DARK_GRAY);
        background.fill();
        setBackground(background);

        addGround(0, 500, 960, 40);
        addWall(90, 400, 20, 100);       
        addWall(490, 400, 20, 100);

        addObject(new Dog(), 200, 470);
        addObject(new Agent(5, 6, 16, "a", "d", "w", "zig"), 400, 300);
        addObject(new Clue(), 300, 470);
        addObject(new Clue(), 600, 470);
        addObject(new Clue(20), 800, 470); 
    }
        
    
    @Override
    protected Level createNew()
    {
        return new Level1();
    }
        
    
    
}
