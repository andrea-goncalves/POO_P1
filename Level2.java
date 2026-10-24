
import greenfoot.*;

public class Level2 extends Level {
    private static final int WHISTLE_RANGE = 250;

    public Level2(){
        super(960, 540);

        loadCollisions("Colisao_fase2.csv");
        setBackground("fase2.png");
        
        addAgents(25, 30, 484);
        
        MovingPlatform platform1 = new MovingPlatform( -6 * TILE_SIZE);
        MovingPlatform platform2 = new MovingPlatform( 5 * TILE_SIZE);
        MovingPlatform platform3 = new MovingPlatform( 5 * TILE_SIZE);
        Door door1 = new Door(0);

        addObject(platform1, 31, 333);
        addObject(platform2, 928, 333);
        addObject(platform3, 512, 142);
        addObject(door1, 450, 475);
        
        addObject(new Computer(door1), 420, 100);
        addObject(new Computer( platform1), 200, 482);
        addObject(new Computer(platform2), 700, 482);
        addObject(new Computer(platform3), 580, 295);
        
        addObject(new Dog(), 512, 295);
        addObject(new Dog(), 920, 100);
        
        addObject(new Clue(20),31,300);
        addObject(new ExitPoint(), 950, 100);
    }

    @Override
    public void usePower(Agent user){
        Greenfoot.playSound("whistle.wav");

        for (Dog dog : getObjects(Dog.class)) {
            double distance = Math.hypot(
                dog.getX() - user.getX(),
                dog.getY() - user.getY()
            );

            if (distance <= WHISTLE_RANGE) {
                dog.neutralize();
            }
        }
    }

    @Override
    public Level createNew(){
        return new Level2();
    }

    @Override
    public int getUnlockScore(){
        return 60;
    }

    @Override
    public String getPowerName(){
        return "Whistle";
    }

    @Override
    public Level nextLevel(){
        return new Level3();
    }
    
     @Override
    public int getLevelNumber()
    {
        return 2;
    }
}
