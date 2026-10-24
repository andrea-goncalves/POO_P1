import greenfoot.*;

public class Level3 extends Level {
    private int lightsOffTimer;
    private int fallTimer;
    private DarknessOverlay overlay;

    public Level3(){
        super(Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT);
        dialogue();
        fallTimer = Settings.FALL_TIMER_OFF;
        lightsOffTimer = 0;
        overlay = null;

        loadCollisions(Settings.LEVEL3_CSV);
        setBackground(Settings.LEVEL3_BACKGROUND);

        addAgents(Settings.L3_ZIG_X, Settings.L3_ZAG_X, Settings.L3_AGENT_Y);

        MovingPlatform platform1 = new MovingPlatform(Settings.L3_PLATFORM1_TILES * Settings.TILE_SIZE);
        MovingPlatform platform2 = new MovingPlatform(Settings.L3_PLATFORM2_TILES * Settings.TILE_SIZE);
        Door door1 = new Door(Settings.L3_DOOR_SECONDS);

        addObject(door1, Settings.L3_DOOR_X, Settings.L3_DOOR_Y);
        addObject(new Computer(door1), Settings.L3_COMPUTER_DOOR_X, Settings.L3_COMPUTER_DOOR_Y);
        addObject(new Computer(platform2), Settings.L3_COMPUTER_PLATFORM_X, Settings.L3_COMPUTER_PLATFORM_Y);
        addObject(platform1, Settings.L3_PLATFORM1_X, Settings.L3_PLATFORM1_Y);
        addObject(platform2, Settings.L3_PLATFORM2_X, Settings.L3_PLATFORM2_Y);

        addObject(new CrushingWall(Settings.L3_CRUSH_LONG_TILES), Settings.L3_CRUSH_LONG_X, Settings.L3_CRUSH_LONG_Y);
        addCrushingRow(Settings.L3_CRUSH_UPPER_X, Settings.L3_CRUSH_UPPER_Y);
        addCrushingRow(Settings.L3_CRUSH_LOWER_X, Settings.L3_CRUSH_LOWER_Y);

        addObject(new Soldier(), Settings.L3_SOLDIER1_X, Settings.L3_SOLDIER1_Y);
        addObject(new Soldier(), Settings.L3_SOLDIER2_X, Settings.L3_SOLDIER2_Y);

        addObject(new Clue(Settings.CLUE_POINTS), Settings.L3_CLUE1_X, Settings.L3_CLUE1_Y);
        addObject(new Clue(Settings.CLUE_POINTS), Settings.L3_CLUE2_X, Settings.L3_CLUE2_Y);
        addObject(new ExitPoint(), Settings.L3_EXIT_X, Settings.L3_EXIT_Y);
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
        if (fallTimer == Settings.FALL_TIMER_OFF) {
            for (Agent agent : getObjects(Agent.class)) {
                if (agent.getFeetY() >= getHeight() - Settings.FALL_EDGE_MARGIN) {
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

    private void addCrushingRow(int[] xPositions, int y){
        for (int x : xPositions) {
            addObject(new CrushingWall(Settings.L3_CRUSH_TILES), x, y);
        }
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