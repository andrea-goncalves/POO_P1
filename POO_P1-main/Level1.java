import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Level1 extends Level
{
    public Level1()
    {
        super(1920, 544);
        /*
        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(Color.DARK_GRAY);
        background.fill();
        setBackground(background);
        */
       //addObject(new ForestBackdrop("floresta_pixel_art_2880x544.png"), getWidth() / 2, getHeight() / 2);
       carregarFundoDividido("floresta_pixel_art_2880x544.png", 1960, 544);
       carregarFundoDividido("lvl1.png", 1960, 544);
       //addObject(new Backdrop("lvl1.png"), getWidth() / 2, getHeight() / 2);
       carregarColisoes("Colisao.csv");

        //addGround(0, 500, 960, 40);
        //addPlatform(300, 380, 200); 
        //addWall(90, 400, 20, 100);       
        //addWall(490, 400, 20, 100);

        //addObject(new Dog(), 200, 470);
        addObject(new Agent(5, 6, 16, "a", "d", "w", "s", "zig"), 30, 0);
        //addObject(new Agent(5, 6, 16, "left", "right", "up", "down", "zag"), 400, 300);
        //addObject(new Clue(), 300, 470);
        //addObject(new Clue(), 600, 470);
        //addObject(new Clue(20), 800, 470); 
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
    /*
    private void carregarColisoes(String caminhoCSV) 
{
    int[][] mapa = CSVReader.carregarMatriz(caminhoCSV);

    for (int linha = 0; linha < mapa.length; linha++) 
    {
        int coluna = 0;
        
        while (coluna < mapa[linha].length) 
        {
            int tipoBloco = mapa[linha][coluna];

            // Bloco 0 é vazio
            if (tipoBloco == 0) {
                coluna++;
                continue;
            }

            // Otimizacao dos blocos
            int inicioColuna = coluna;
            while (coluna < mapa[linha].length && mapa[linha][coluna] == tipoBloco) {
                coluna++;
            }

            int blocosConsecutivos = coluna - inicioColuna;

            int larguraPlataforma = blocosConsecutivos * TILE_SIZE;
            int xCentral = (inicioColuna * TILE_SIZE) + (larguraPlataforma / 2);
            int yCentral = (linha * TILE_SIZE) + (TILE_SIZE / 2);

            switch (tipoBloco) 
            {
                case 1: // Chão
                    addObject(new Ground(larguraPlataforma, TILE_SIZE), xCentral, yCentral);
                    break;
                    
                case 2: // Parede
                    addObject(new Wall(larguraPlataforma, TILE_SIZE), xCentral, yCentral);
                    break;
            }
        }
    }
}
}
*/
    
    private void carregarColisoes(String caminhoCSV) 
    {
        int[][] mapa = CSVReader.carregarMatriz(caminhoCSV);

        for (int linha = 0; linha < mapa.length; linha++) 
        {
            for (int coluna = 0; coluna < mapa[linha].length; coluna++) 
            {
                int tipoBloco = mapa[linha][coluna];

                if (tipoBloco != 0) 
                {
                    int x = coluna * TILE_SIZE + (TILE_SIZE / 2);
                    int y = linha * TILE_SIZE + (TILE_SIZE / 2);

                    switch (tipoBloco) 
                    {
                        case 1: // Chão
                            addObject(new Ground(TILE_SIZE, TILE_SIZE), x, y);
                            break;
                            
                        case 2: // Parede
                            addObject(new Wall(TILE_SIZE, TILE_SIZE), x, y);
                            break;
                            
                        //case 3: // Armadilha
                            //addObject(new Trap(TILE_SIZE, TILE_SIZE), x, y);
                            //break;
                            
                        //case 4: // Escada
                            //addObject(new Ladder(TILE_SIZE, TILE_SIZE), x, y);
                            //break;
                    }
                }
            }
        }
    }
    
}



