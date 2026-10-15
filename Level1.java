import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Level1 extends Level
    
{
    public Level1()
    {
        super(960, 540);

        //GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        //background.setColor(Color.DARK_GRAY);
        //background.fill();
        //setBackground(background);

        //addGround(0, 500, 960, 40);
        
        //addPlatform(300, 380, 200); 
        //addWall(90, 400, 20, 100);       
        //addWall(490, 400, 20, 100);
        carregarColisoes("Colisao_fase1.csv");
        setBackground("_composite.png");

        //addObject(new Dog(), 200, 470);
        addObject(new Agent(5, 6, 16, "a", "d", "w", "s", "zig"), 0, 0);
        //addObject(new Agent(5, 6, 16, "left", "right", "up", "down", "zag"), 400, 300);
        addObject(new Clue(), 300, 470);
        addObject(new Clue(), 600, 470);
        addObject(new Clue(20), 800, 470); 
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
    protected Level createNew()
    {
        return new Level1();
    }
    @Override
    public int getUnlockScore()
    {
        return 40;
    }
    @Override
    public String getPowerName()
    {
        return "Whistle";
    }
    @Override
    public void usePower(Agent user){
        Greenfoot.playSound("whistle.wav");
        
        for (Dog dog : getObjects(Dog.class)) {
            double distance = Math.hypot(dog.getX() - user.getX(), dog.getY() - user.getY());
            if (distance <= 250) {
                dog.neutralize();
            }
        }
    }        
}
