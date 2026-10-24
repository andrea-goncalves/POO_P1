import greenfoot.*; 

public abstract class Level extends World
{
    private int score;
    private int startScore; 
    private boolean powerUnlocked;
    private boolean pauseKeyHeld;

    public Level(int width, int height)
    {    
        super(width, height, 1, false); 
        score = 0;
        startScore = 0;
        powerUnlocked = false;
        pauseKeyHeld = true;
        setPaintOrder(Dialogue.class, HUD.class,
            DarknessOverlay.class, Agent.class, Enemy.class, Bullet.class, Clue.class, Solid.class);
        addObject(new HUD(width), width / 2, Settings.HUD_Y);
    }

    @Override
    public void act(){
        checkPause();
        checkPowerUnlock();
        checkRestart();
    }

    public abstract Level nextLevel();

    public abstract Level createNew();

    public abstract int getLevelNumber();

    public void addAgents(int zigX, int zagX, int y)
    {
        addObject(new Agent(Settings.AGENT_LIVES, Settings.AGENT_SPEED, Settings.AGENT_JUMP,
                Settings.ZIG_LEFT, Settings.ZIG_RIGHT, Settings.ZIG_JUMP, Settings.ZIG_POWER,
                Settings.ZIG_ATTACK, Settings.ZIG_ATTACK_FRAMES, Settings.ZIG_PREFIX), zigX, y);

        addObject(new Agent(Settings.AGENT_LIVES, Settings.AGENT_SPEED, Settings.AGENT_JUMP,
                Settings.ZAG_LEFT, Settings.ZAG_RIGHT, Settings.ZAG_JUMP, Settings.ZAG_POWER,
                Settings.ZAG_ATTACK, Settings.ZAG_ATTACK_FRAMES, Settings.ZAG_PREFIX), zagX, y);
    }

    public void loadCollisions(String csvPath) {
        int[][] map = CSVReader.matrix(csvPath);

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                int x = column * Settings.TILE_SIZE + Settings.TILE_SIZE / 2;
                int y = row * Settings.TILE_SIZE + Settings.TILE_SIZE / 2;
                int tile = map[row][column];

                if (tile == Settings.TILE_SOLID) {
                    addObject(new Solid(), x, y);
                }
                else if (tile == Settings.TILE_TRAP) {
                    addObject(new Trap(), x, y);
                }
            }
        }
    }

    public void advanceLevel() {
        Level next = nextLevel();

        if (next != null) {
            addLevelBonus();
            next.setStartingScore(score);
            Greenfoot.setWorld(next);
        }
    }

    public void addScore(int points){
        score += points;
    }

    public int getScore(){
        return score;
    }

    public void setStartingScore(int points)
    {
        score = points;
        startScore = points;
    }

    public int getStartScore()
    {
        return startScore;
    }

    public int getLevelScore()
    {
        return score - startScore;
    }

    public void addLevelBonus(){
        addScore(Settings.LEVEL_COMPLETE_BONUS);
    }

    public boolean isPowerUnlocked(){
        return powerUnlocked;
    }

    public int getUnlockScore() { return Settings.DEFAULT_UNLOCK_SCORE; }

    public String getPowerName() { return Settings.POWER_NONE; }

    public void usePower(Agent user) { }

    public boolean allowsAttack()
    {
        return false;
    }

    public boolean usesShield()
    {
        return false;
    }

    public boolean isDialogueActive(){
        return !getObjects(Dialogue.class).isEmpty();
    }

    private void checkPause()
    {
        boolean pauseDown = Greenfoot.isKeyDown(Settings.KEY_PAUSE);
        if (pauseDown && !pauseKeyHeld) {
            Greenfoot.setWorld(new PauseWorld(this));
        }
        pauseKeyHeld = pauseDown;
    }

    private void checkRestart()
    {
        for (Agent agent : getObjects(Agent.class)) {
            if (agent.getLives() <= 0) {
                Greenfoot.setWorld(new GameOverWorld(this));
                return;
            }
        }
    }

    private void checkPowerUnlock(){
        if (!powerUnlocked && score >= getUnlockScore()) {
            powerUnlocked = true;
        }
    }
}

