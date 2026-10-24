import greenfoot.*; 

public abstract class Level extends World
{
    protected abstract Level nextLevel();
    private static final int AGENT_LIVES = 5;
    private static final int AGENT_SPEED = 6;
    private static final int AGENT_JUMP = 16;
    public static final int TILE_SIZE = 32;
    private static final int LEVEL_COMPLETE_BONUS = 10;
    private int score;
    private int startScore; 
    private boolean powerUnlocked;
    private boolean pauseKeyHeld;

    Level(int width, int height)
    {    
        super(width, height, 1, false); 
        score = 0;
        startScore = 0;
        powerUnlocked = false;
        pauseKeyHeld = true;
        setPaintOrder(Dialogue.class,HUD.class,
        DarknessOverlay.class, Agent.class, Enemy.class,Bullet.class, Clue.class, Solid.class);
        addObject(new HUD(width), width / 2, 15);
    }

    public void act(){
        checkPause();
        checkPowerUnlock();
        checkRestart();

    }
    protected void addAgents(int zigX, int zagX, int y)
    {
        addObject(new Agent(AGENT_LIVES, AGENT_SPEED, AGENT_JUMP, "a", "d", "w", "s", "zig"), zigX, y);
        addObject(new Agent(AGENT_LIVES, AGENT_SPEED, AGENT_JUMP, "left", "right", "up", "down", "zag"), zagX, y);
    }
    
    public abstract int getLevelNumber();

    protected abstract Level createNew();

    private void checkPause()
    {
        boolean pauseDown = Greenfoot.isKeyDown("p");
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

    public void advanceLevel() {
        Level next = nextLevel();

        if (next != null) {
            addScore(LEVEL_COMPLETE_BONUS);
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

    private void checkPowerUnlock(){
        if (!powerUnlocked && score >= getUnlockScore()) {
            powerUnlocked = true;
        }
    }

    public boolean isPowerUnlocked(){
        return powerUnlocked;
    }

    protected void loadCollisions(String csvPath) {
        int[][] map = CSVReader.matrix(csvPath);

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                if (map[row][column] == 1) {
                    int x = column * TILE_SIZE + TILE_SIZE / 2;
                    int y = row * TILE_SIZE + TILE_SIZE / 2;
                    addObject(new Solid(), x, y);
                }
                if (map[row][column] == 2) {
                    int x = column * TILE_SIZE + TILE_SIZE / 2;
                    int y = row * TILE_SIZE + TILE_SIZE / 2;
                    addObject(new Trap(), x, y);
                }
            }
        }
    }

    public abstract int getUnlockScore();

    public abstract String getPowerName();

    public abstract void usePower(Agent user);

    public boolean allowsAttack()
    {
        return false;
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
        addScore(LEVEL_COMPLETE_BONUS);
    }
    
    public boolean isDialogueActive(){
        return !getObjects(Dialogue.class).isEmpty();
    }
}
