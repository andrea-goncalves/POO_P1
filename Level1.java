import greenfoot.*; 

public class Level1 extends Level
{
    public Level1(){
        super(Settings.WORLD_WIDTH, Settings.LEVEL1_HEIGHT);
        dialogue();
        loadCollisions(Settings.LEVEL1_CSV);
        setBackground(Settings.LEVEL1_BACKGROUND);

        addAgents(25, 30, 484);

        Door door1 = new Door(2);
        Door door2 = new Door(0);

        MovingPlatform platform1 = new MovingPlatform(-5 * Settings.TILE_SIZE);

        addObject(platform1, 770, 335);

        addObject(door1, 155, 190);
        addObject(door2, 910, 475);

        addObject(new Computer(door1), 600, 484);
        addObject(new Computer(platform1), 50, 190);
        addObject(new Computer(door1, door2), 500, 100);

        addObject(new Clue(Settings.CLUE_POINTS), 160, 400);
        addObject(new Clue(Settings.CLUE_POINTS), 650, 100);

        addObject(new ExitPoint(), 955, 490);
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