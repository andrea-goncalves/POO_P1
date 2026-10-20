import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Level1 extends Level
    
{
     public Level1(){
        super(960, 544);

        carregarColisoes("Colisao_fase1.csv");
        setBackground("fase1.png");
    
        addObject(new Agent(5, 6, 16, "a", "d", "w", "s", "zig"), 25, 484);
        addObject(new Agent(5, 6, 16, "left", "right", "up", "down", "zag"), 30, 484);
    
        Door door1 = new Door(2);
        Door door2 = new Door(0);
    
        MovingPlatform plataform1 = new MovingPlatform(0 , -5 * TILE_SIZE);
        

        addObject(plataform1, 770, 335);
    
        addObject(door1, 155, 190);
        addObject(door2, 910, 475);
        
    
        addObject(new Computer(door1), 600, 484);
        addObject(new Computer(new Door[]{door1},new MovingPlatform[]{plataform1}), 50, 190);
        addObject(new Computer(new Door[]{door2},new MovingPlatform[]{plataform1}), 500, 100);
        
        addObject(new Clue(20),160,20);
        addObject(new Clue(20),650,100);
        
        addObject(new ExitPoint(),955,490);
    }        
    @Override
    protected Level createNew()
    {
        return new Level1();
    }
    @Override
    public int getUnlockScore()
    {
        return 0;
    }
    @Override
    public String getPowerName(){
        return "None";
    }
    @Override
    public void usePower(Agent user){    }
    @Override
    protected Level nextLevel() {
    return new Level2();
    }
    @Override
    public int getLevelNumber()
    {
        return 1;
    }
}
