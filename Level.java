import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class Level extends World
{
    protected abstract Level nextLevel();
    public static final int TILE_SIZE = 32;
    private int score = 0;
    private boolean powerUnlocked = false;
    //private static final int PLATFORM_THICKNESS = 16;
    private boolean pauseKeyHeld = true;
    public abstract int getLevelNumber();
    Level(int width, int height)
    {    
        super(width, height, 1);
        setPaintOrder(HUD.class, DarknessOverlay.class, Agent.class, Enemy.class,Bullet.class, Clue.class, Scenery.class);
        addObject(new HUD(width), width / 2, 15);
    }
    public void act(){
        checkPause();
        checkPowerUnlock();
        checkRestart();
        
    }
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
            Greenfoot.setWorld(next);
        }
    }
    /*
    protected void addWall(int x, int y, int w, int h)
    {
        addObject(new Wall(w, h), x + w / 2, y + h / 2);
    }
    
    protected void addGround(int x, int y, int w, int h)
    {
        addObject(new Ground(w, h), x + w / 2, y + h / 2);
    }
    
   
    protected void addPlatform(int x, int y, int w){
        //addObject(new Ceiling(w, PLATFORM_THICKNESS), x + w / 2, y + PLATFORM_THICKNESS / 2);
        addGround(x, y, w, PLATFORM_THICKNESS);
    }
    */
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
    protected void carregarColisoes(String caminhoCSV){
        int[][] mapa = CSVReader.carregarMatriz(caminhoCSV);
    
        for (int linha = 0; linha < mapa.length; linha++) {
            for (int coluna = 0; coluna < mapa[linha].length; coluna++) {
                if (mapa[linha][coluna] == 1) {
                    int x = coluna * TILE_SIZE + TILE_SIZE / 2;
                    int y = linha * TILE_SIZE + TILE_SIZE / 2;
                    addObject(new Solid(), x, y);
                }
                if (mapa[linha][coluna] == 2) {
                    int x = coluna * TILE_SIZE + TILE_SIZE / 2;
                    int y = linha * TILE_SIZE + TILE_SIZE / 2;
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
}
