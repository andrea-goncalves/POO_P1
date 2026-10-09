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
        //addWall(90, 400, 20, 100);       
        //addWall(490, 400, 20, 100);

        addObject(new Dog(), 200, 470);
        addObject(new Agent(5, 6, 16, "a", "d", "w", "s", "zig"), 400, 300);
        //addObject(new Agent(5, 6, 16, "left", "right", "up", "down", "zag"), 400, 300);
        addObject(new Clue(), 300, 470);
        addObject(new Clue(), 600, 470);
        addObject(new Clue(20), 800, 470); 
    }
        
    @Override
    protected Level createNew()
    {
        return new Level1();
    }
    @Override
    public int getUnlockScore()
    {
        return 40;
    }
    
    @Override
    public String getPowerName()
    {
        return "Whistle";
    }
    
    @Override
    public void usePower(Agent user){
        Greenfoot.playSound("whistle.wav");
        
        for (Dog dog : getObjects(Dog.class)) {
            double distance = Math.hypot(dog.getX() - user.getX(), dog.getY() - user.getY());
            if (distance <= 250) {
                dog.neutralize();
            }
        }
    }        
    
    
}
