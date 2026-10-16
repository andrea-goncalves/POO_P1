import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Level2 extends Level
{
    private static final int LIGHTS_OFF_TIME = 300;    
    private static final int SHORT_SIGHT = 100;        
    private int lightsOffTimer = 0;
    private DarknessOverlay overlay;
    public Level2(){
        super(960, 540);
        carregarColisoes("Colisao_fase2.csv");
        setBackground("fase2.png");
        addObject(new Agent(5, 6, 16, "a", "d", "w", "s", "zig"), 25, 484);
        addObject(new Agent(5, 6, 16, "left", "right", "up", "down", "zag"), 30, 484);
        MovingPlatform plataform1 = new MovingPlatform(0, -6 * TILE_SIZE);
        MovingPlatform plataform2 = new MovingPlatform(0, 5 * TILE_SIZE);
        MovingPlatform plataform3 = new MovingPlatform(0, 5 * TILE_SIZE);
        Door door1 = new Door(0);
        
        addObject(plataform1, 31, 333);
        addObject(plataform2, 928, 333);
        addObject(plataform3, 512, 142);
        addObject(door1,450,475);
        addObject(new Computador(new Door[]{},new MovingPlatform[]{plataform1}), 200, 482);
        addObject(new Computador(door1), 420, 100);
        //addObject(new Computador(door1), 420, 500);
        addObject(new Computador(new Door[]{},new MovingPlatform[]{plataform2}), 700, 482);
        addObject(new Computador(new Door[]{},new MovingPlatform[]{plataform3}), 580, 295);
        
        addObject(new Dog(),512,295);
        addObject(new Dog(),920,100);
        //addObject(new Soldier(),25,100);
    
    }
    private void carregarColisoes(String caminhoCSV)
    {
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
    @Override
        public void usePower(Agent user){
            // Greenfoot.playSound("switch.wav");
            lightsOffTimer = LIGHTS_OFF_TIME;                
            if (overlay == null) {                            
                overlay = new DarknessOverlay(getWidth(), getHeight());
                addObject(overlay, getWidth() / 2, getHeight() / 2);
            }
            for (Soldier soldier : getObjects(Soldier.class)) {
                soldier.setSightRange(SHORT_SIGHT);
            }
    }
    @Override
    public void act(){
        super.act();                                      
    
        if (lightsOffTimer > 0) {
            lightsOffTimer--;
            if (lightsOffTimer == 0) {
                lightsOn();
            }
        }
    }
    private void lightsOn(){
        removeObject(overlay);
        overlay = null;
        for (Soldier soldier : getObjects(Soldier.class)) {
            soldier.resetSightRange();
        }
    }
    @Override
    protected Level createNew(){
        return new Level2();
    }
    @Override
    public int getUnlockScore(){
        return 40;
    }
    @Override
    public String getPowerName(){
        return "Lights off";
    } 
}
