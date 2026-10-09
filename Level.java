import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class Level extends World
{

    private int score = 0;
    private boolean powerUnlocked = false;
    private static final int PLATFORM_THICKNESS = 16;
    
    Level(int width, int height)
    {    
        super(width, height, 1);
        setPaintOrder(HUD.class, DarknessOverlay.class, Agent.class, Enemy.class,Bullet.class, Clue.class, Scenery.class, Ceiling.class);
        addObject(new HUD(width), width / 2, 15);
    }
    public void act(){
        checkPowerUnlock();
        checkRestart();
        
    }
    protected abstract Level createNew();
    private void checkRestart()
    {
        for (Agent agent : getObjects(Agent.class)) {
            if (agent.getLives() <= 0) {
                Greenfoot.setWorld(createNew());
                return;
            }
        }
    }
    protected void addWall(int x, int y, int w, int h)
    {
        addObject(new Wall(w, h), x + w / 2, y + h / 2);
    }
    protected void addGround(int x, int y, int w, int h)
    {
        addObject(new Ground(w, h), x + w / 2, y + h / 2);
    }
    protected void addPlatform(int x, int y, int w){
        addObject(new Ceiling(w, PLATFORM_THICKNESS), x + w / 2, y + PLATFORM_THICKNESS / 2);
        addGround(x, y, w, PLATFORM_THICKNESS);
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
    public abstract int getUnlockScore();
    public abstract String getPowerName();
    public abstract void usePower(Agent user);
       
}
