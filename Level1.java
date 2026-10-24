import greenfoot.*; 

public class Level1 extends Level
    
{
     public Level1(){
        super(960, 544);
        dialogue();
        loadCollisions("Colisao_fase1.csv");
        setBackground("fase1.png");
    
        addAgents(25, 30, 484);
    
        Door door1 = new Door(2);
        Door door2 = new Door(0);
    
        MovingPlatform platform1 = new MovingPlatform(-5 * TILE_SIZE);
        

        addObject(platform1, 770, 335);
    
        addObject(door1, 155, 190);
        addObject(door2, 910, 475);
        
    
        addObject(new Computer(door1), 600, 484);
        addObject(new Computer(platform1), 50, 190);
        addObject(new Computer(door1,door2), 500, 100);
        
        
        addObject(new Clue(20),160,400);
        addObject(new Clue(20),650,100);
        
        addObject(new ExitPoint(),955,490);
    }        
    @Override
    public Level createNew()
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
    public Level nextLevel() {
    return new Level2();
    }
    @Override
    public int getLevelNumber()
    {
        return 1;
    }
    public void dialogue(){
        String[] names = {"ZAG", "ZIG"};

        String[] texts = {
            "O Projeto Cronos funcionou! Voltamos dois dias no tempo.",
            "Temos que subir a montanha e impedir o lançamento da bomba!"
        };

        addObject(new Dialogue(names, texts), 480, 450);
    }
}
