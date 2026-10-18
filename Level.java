import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public abstract class Level extends World
{
    protected abstract Level nextLevel();
    public static final int TILE_SIZE = 32;
    private int score = 0;
    private boolean powerUnlocked = false;
    private static final int PLATFORM_THICKNESS = 16;
    
    public Level(int width, int height)
    {    
        super(width, height, 1, false); 
        
        
        setPaintOrder(
            HUD.class, 
            DarknessOverlay.class, 
            Agent.class, 
            Enemy.class, 
            Bullet.class, 
            Clue.class, 
            Scenery.class
            
        );
        
        addObject(new HUD(width), width / 2, 15);
    }

    @Override
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

    public void advanceLevel() {
        Level next = nextLevel();

        if (next != null) {
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
}