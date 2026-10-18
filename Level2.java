
import greenfoot.*;

public class Level2 extends Level {
    private static final int WHISTLE_RANGE = 250;

    public Level2(){
        super(960, 540);

        carregarColisoes("Colisao_fase2.csv");
        setBackground("fase2.png");

        addObject(new Agent(5, 6, 16, "a", "d", "w", "s", "zig"), 25, 484);
        addObject(new Agent(5, 6, 16, "left", "right", "up", "down", "zag"), 30, 484);

        MovingPlatform plataform1 = new MovingPlatform(0, -6 * TILE_SIZE);
        MovingPlatform plataform2 = new MovingPlatform(0, 5 * TILE_SIZE);
        MovingPlatform plataform3 = new MovingPlatform(0, 5 * TILE_SIZE);
        Door door1 = new Door(0);

        addObject(plataform1, 31, 333);
        addObject(plataform2, 928, 333);
        addObject(plataform3, 512, 142);
        addObject(door1, 450, 475);

        addObject(new Computador(new Door[]{}, new MovingPlatform[]{plataform1}), 200, 482);
        addObject(new Computador(door1), 420, 100);
        addObject(new Computador(new Door[]{}, new MovingPlatform[]{plataform2}), 700, 482);
        addObject(new Computador(new Door[]{}, new MovingPlatform[]{plataform3}), 580, 295);

        addObject(new Dog(), 512, 295);
        addObject(new Dog(), 920, 100);
        
        addObject(new Clue(20),31,300);
        addObject(new Clue(20),750,100);
        
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
    protected Level createNew(){
        return new Level2();
    }

    @Override
    public int getUnlockScore(){
        return 40;
    }

    @Override
    public String getPowerName(){
        return "Whistle";
    }

    @Override
    protected Level nextLevel(){
        return new Level3();
    }
}
