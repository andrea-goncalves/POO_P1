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
        //Door door2 = new Door(5);
        Door door2 = new Door(0);
    
        MovingPlatform plataform1 = new MovingPlatform(0 , -5 * TILE_SIZE);
        //MovingPlatform plataform2 = new MovingPlatform(0, -5 * TILE_SIZE);

        addObject(plataform1, 770, 335);
    
        addObject(door1, 155, 190);
        //addObject(door2, 510, 90);
        addObject(door2, 910, 475);
        addObject(plataform1, 785, 335);
    
        addObject(new Computador(door1), 600, 484);
        //addObject(new Computador(door2), 50, 190);
        //Se a pista abrir a última porta,não precisa que um computador abre várias coisas
        addObject(new Computador(new Door[]{door1},new MovingPlatform[]{plataform1}), 50, 190);
        addObject(new Computador(new Door[]{door2},new MovingPlatform[]{plataform1}), 500, 100);
        
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
