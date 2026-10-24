import greenfoot.*;

public class Level3 extends Level {
    private int lightsOffTimer;
    private int fallTimer;
    private DarknessOverlay overlay;

    public Level3(){
        super(Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT);
        dialogue();
        fallTimer = -1;
        lightsOffTimer = 0;
        overlay = null;

        loadCollisions(Settings.LEVEL3_CSV);
        setBackground(Settings.LEVEL3_BACKGROUND);

        addAgents(25, 30, 135);

        MovingPlatform platform1 = new MovingPlatform(-4 * Settings.TILE_SIZE);
        MovingPlatform platform2 = new MovingPlatform(3 * Settings.TILE_SIZE);

        Door door1 = new Door(5);

        addObject(door1, 800, 30);
        addObject(new Computer(door1), 642, 40);
        addObject(new Computer(platform2), 585, 455);
        addObject(platform1, 350, 490);
        addObject(platform2, 448, 302);

        addObject(new CrushingWall(2), 320, 35);
        addObject(new CrushingWall(1), 832, 207);
        addObject(new CrushingWall(1), 704, 207);
        addObject(new CrushingWall(1), 576, 207);
        addObject(new CrushingWall(1), 96, 207);
        addObject(new CrushingWall(1), 768, 357);
        addObject(new CrushingWall(1), 640, 357);
        addObject(new CrushingWall(1), 512, 357);

        addObject(new Soldier(), 455, 135);
        addObject(new Soldier(), 900, 50);

        addObject(new Clue(Settings.CLUE_POINTS), 420, 80);
        addObject(new Clue(Settings.CLUE_POINTS), 500, 250);
        addObject(new ExitPoint(), 870, 450);
    }

    @Override
    public void act(){
        if (checkFallenAgents()) {
            return;
        }

        super.act();

        if (lightsOffTimer > 0) {
            lightsOffTimer--;

            if (lightsOffTimer == 0) {
                lightsOn();
            }
        }
    }

    @Override
    public void usePower(Agent user){
        Greenfoot.playSound(Settings.SOUND_LIGHTS);

        lightsOffTimer = Settings.LIGHTS_OFF_TIME;

        if (overlay == null) {
            overlay = new DarknessOverlay(getWidth(), getHeight());
            addObject(overlay, getWidth() / 2, getHeight() / 2);
        }

        for (Soldier soldier : getObjects(Soldier.class)) {
            soldier.setSightRange(Settings.LIGHTS_SHORT_SIGHT);
        }
    }

    @Override
    public Level createNew(){
        return new Level3();
    }

    @Override
    public int getUnlockScore(){
        return Settings.LIGHTS_UNLOCK_SCORE;
    }

    @Override
    public String getPowerName(){
        return Settings.POWER_LIGHTS;
    }

    @Override
    public Level nextLevel(){
        return new BossLevel();
    }

    @Override
    public int getLevelNumber()
    {
        return Settings.LEVEL_3;
    }

    private void lightsOn(){
        if (overlay != null) {
            removeObject(overlay);
            overlay = null;
        }

        for (Soldier soldier : getObjects(Soldier.class)) {
            soldier.resetSightRange();
        }
    }

    private boolean checkFallenAgents()
    {
        if (fallTimer < 0) {
            for (Agent agent : getObjects(Agent.class)) {
                if (agent.getFeetY() >= getHeight() - 1) {
                    fallTimer = Settings.FALL_GAME_OVER_DELAY;
                    break;
                }
            }
            return false;
        }

        fallTimer--;
        if (fallTimer <= 0) {
            Greenfoot.setWorld(new GameOverWorld(this));
            return true;
        }
        return false;
    }

    private void dialogue(){
        String[] names = {"ZAG", "ZIG"};

        String[] texts = {
                "O General embarcou na Fortaleza Voadora com a bomba!",
                "Vamos para o elevador! Temos que impedir que o avião descole!"
            };

        addObject(new Dialogue(names, texts), Settings.DIALOGUE_X, Settings.DIALOGUE_Y);
    }
}