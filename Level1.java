import greenfoot.*; 

public class Level1 extends Level
{
    public Level1(){
        super(Settings.WORLD_WIDTH, Settings.LEVEL1_HEIGHT);
        dialogue();
        loadCollisions(Settings.LEVEL1_CSV);
        setBackground(Settings.LEVEL1_BACKGROUND);

        addAgents(Settings.L1_ZIG_X, Settings.L1_ZAG_X, Settings.L1_AGENT_Y);

        Door door1 = new Door(Settings.L1_DOOR1_SECONDS);
        Door door2 = new Door(Settings.L1_DOOR2_SECONDS);

        MovingPlatform platform1 = new MovingPlatform(Settings.L1_PLATFORM_TILES * Settings.TILE_SIZE);

        addObject(platform1, Settings.L1_PLATFORM_X, Settings.L1_PLATFORM_Y);
        addObject(door1, Settings.L1_DOOR1_X, Settings.L1_DOOR1_Y);
        addObject(door2, Settings.L1_DOOR2_X, Settings.L1_DOOR2_Y);

        addObject(new Computer(door1), Settings.L1_COMPUTER_DOOR1_X, Settings.L1_COMPUTER_DOOR1_Y);
        addObject(new Computer(platform1), Settings.L1_COMPUTER_PLATFORM_X, Settings.L1_COMPUTER_PLATFORM_Y);
        addObject(new Computer(door1, door2), Settings.L1_COMPUTER_DOORS_X, Settings.L1_COMPUTER_DOORS_Y);

        addObject(new Clue(Settings.CLUE_POINTS), Settings.L1_CLUE1_X, Settings.L1_CLUE1_Y);
        addObject(new Clue(Settings.CLUE_POINTS), Settings.L1_CLUE2_X, Settings.L1_CLUE2_Y);

        addObject(new ExitPoint(), Settings.L1_EXIT_X, Settings.L1_EXIT_Y);
    }

    @Override
    public Level createNew()
    {
        return new Level1();
    }

    @Override
    public Level nextLevel() {
        return new Level2();
    }

    @Override
    public int getLevelNumber()
    {
        return Settings.LEVEL_1;
    }

    private void dialogue(){
        String[] names = {"ZAG", "ZIG"};

        String[] texts = {
                "O Projeto Cronos funcionou! Voltamos dois dias no tempo.",
                "Temos que subir a montanha e impedir o lançamento da bomba!"
            };

        addObject(new Dialogue(names, texts), Settings.DIALOGUE_X, Settings.DIALOGUE_Y);
    }
}