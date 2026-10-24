import greenfoot.*;

public class Level2 extends Level {
    public Level2(){
        super(Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT);

        loadCollisions(Settings.LEVEL2_CSV);
        setBackground(Settings.LEVEL2_BACKGROUND);

        addAgents(Settings.L2_ZIG_X, Settings.L2_ZAG_X, Settings.L2_AGENT_Y);

        MovingPlatform platform1 = new MovingPlatform(Settings.L2_PLATFORM1_TILES * Settings.TILE_SIZE);
        MovingPlatform platform2 = new MovingPlatform(Settings.L2_PLATFORM2_TILES * Settings.TILE_SIZE);
        MovingPlatform platform3 = new MovingPlatform(Settings.L2_PLATFORM3_TILES * Settings.TILE_SIZE);
        Door door1 = new Door(Settings.L2_DOOR_SECONDS);

        addObject(platform1, Settings.L2_PLATFORM1_X, Settings.L2_PLATFORM1_Y);
        addObject(platform2, Settings.L2_PLATFORM2_X, Settings.L2_PLATFORM2_Y);
        addObject(platform3, Settings.L2_PLATFORM3_X, Settings.L2_PLATFORM3_Y);
        addObject(door1, Settings.L2_DOOR_X, Settings.L2_DOOR_Y);

        addObject(new Computer(door1), Settings.L2_COMPUTER_DOOR_X, Settings.L2_COMPUTER_DOOR_Y);
        addObject(new Computer(platform1), Settings.L2_COMPUTER_PLATFORM1_X, Settings.L2_COMPUTER_PLATFORM1_Y);
        addObject(new Computer(platform2), Settings.L2_COMPUTER_PLATFORM2_X, Settings.L2_COMPUTER_PLATFORM2_Y);
        addObject(new Computer(platform3), Settings.L2_COMPUTER_PLATFORM3_X, Settings.L2_COMPUTER_PLATFORM3_Y);

        addObject(new Dog(), Settings.L2_DOG1_X, Settings.L2_DOG1_Y);
        addObject(new Dog(), Settings.L2_DOG2_X, Settings.L2_DOG2_Y);

        addObject(new Clue(Settings.CLUE_POINTS), Settings.L2_CLUE_X, Settings.L2_CLUE_Y);
        addObject(new ExitPoint(), Settings.L2_EXIT_X, Settings.L2_EXIT_Y);
    }

    @Override
    public void usePower(Agent user){
        Greenfoot.playSound(Settings.SOUND_WHISTLE);

        for (Dog dog : getObjects(Dog.class)) {
            double distance = Math.hypot(
                    dog.getX() - user.getX(),
                    dog.getY() - user.getY()
                );

            if (distance <= Settings.WHISTLE_RANGE) {
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
        return Settings.WHISTLE_UNLOCK_SCORE;
    }

    @Override
    public String getPowerName(){
        return Settings.POWER_WHISTLE;
    }

    @Override
    public Level nextLevel(){
        return new Level3();
    }

    @Override
    public int getLevelNumber()
    {
        return Settings.LEVEL_2;
    }
}