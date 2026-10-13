import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public abstract class Level extends World
{

    private int score = 0;
    private boolean powerUnlocked = false;
    private static final int PLATFORM_THICKNESS = 16;
    public static final int TILE_SIZE = 16;
    
    Level(int width, int height)
    {    
        super(width, height, 1,false);
        setPaintOrder(
        HUD.class, 
        DarknessOverlay.class, 
        Agent.class, 
        Enemy.class, 
        Ground.class, 
        Wall.class, 
        Backdrop.class,       
        ForestBackdrop.class  
    );
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
    /**
     * Função pra otimizar o tamanho da imagem, divide ela em dois e carrega separadamente
     */
    protected void carregarFundoDividido(String caminhoImagem, int larguraTotal, int altura) 
{
    GreenfootImage imagemCompleta = new GreenfootImage(caminhoImagem);
    
    int metadeLargura = larguraTotal / 2;

    //imagem para a metade esquerda
    GreenfootImage parteEsquerda = new GreenfootImage(metadeLargura, altura);
    parteEsquerda.drawImage(imagemCompleta, 0, 0);

    //imagem para a metade direita
    GreenfootImage parteDireita = new GreenfootImage(metadeLargura, altura);
    parteDireita.drawImage(imagemCompleta, -metadeLargura, 0);

    Backdrop bgEsquerda = new Backdrop(parteEsquerda);
    Backdrop bgDireita = new Backdrop(parteDireita);

    // Adicionar nas posições corretas cada metade
    addObject(bgEsquerda, metadeLargura / 2, altura / 2);
    
    // Parte direita
    addObject(bgDireita, metadeLargura + (metadeLargura / 2), altura / 2);
}
    public void scrollWorld(int distance) {
    List<Actor> actors = getObjects(Actor.class);
    for (Actor actor : actors) {
        // Para mover todos os objetos, exceto a HUD
        if (!(actor instanceof Agent) && !(actor instanceof HUD) && !(actor instanceof DarknessOverlay)) {
            actor.setLocation(actor.getX() + distance, actor.getY());
        }
    }
}
    public abstract int getUnlockScore();
    public abstract String getPowerName();
    public abstract void usePower(Agent user);
       
}
