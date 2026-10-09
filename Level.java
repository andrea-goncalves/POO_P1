import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class Level extends World
{

    private int score = 0;
    private boolean powerUnlocked = false;

    
    Level(int width, int height)
    {    
        super(width, height, 1);
        setPaintOrder(Agent.class, Enemy.class,Clue.class, Scenery.class);
        addObject(new HUD(width), width / 2, 15);
    }
    
    protected void addWall(int x, int y, int w, int h)
    {
        addObject(new Wall(w, h), x + w / 2, y + h / 2);
    }
    protected void addGround(int x, int y, int w, int h)
    {
        addObject(new Ground(w, h), x + w / 2, y + h / 2);
    }
    
    protected abstract Level createNew();
    
    public void act(){
        checkPowerUnlock();
        checkRestart();
        
    }

    private void checkRestart()
    {
        for (Agent agent : getObjects(Agent.class)) {
            if (agent.getLives() <= 0) {
                Greenfoot.setWorld(createNew());
                return;
            }
        }
    }
    
        public void addScore(int points){
        score += points;
    }
        public int getScore(){
        return score;
    }
    
    public abstract int getUnlockScore();
    public abstract String getPowerName();
    public abstract void usePower(Agent user);
    public boolean isPowerUnlocked(){
        return powerUnlocked;
    }
    private void checkPowerUnlock(){
        if (!powerUnlocked && score >= getUnlockScore()) {
            powerUnlocked = true;
        }
    }
    
}
