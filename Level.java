import greenfoot.*; 
/**
 * Classe base de todos os níveis. Guarda a pontuação e o estado do poder,
 * trata da pausa, do fim de jogo e da passagem para o nível seguinte.
 */
public abstract class Level extends World
{
    private int score;
    private int startScore; 
    private boolean powerUnlocked;
    private boolean pauseKeyHeld;
    // Cria o mundo, define a ordem de desenho e adiciona o HUD. 
    public Level(int width, int height)
    {    
        super(width, height, Settings.CELL_SIZE, Settings.WORLD_BOUNDED);
        score = 0;
        startScore = 0;
        powerUnlocked = false;
        pauseKeyHeld = true;
        setPaintOrder(Dialogue.class, HUD.class,
            DarknessOverlay.class, Agent.class, Enemy.class, Bullet.class, Clue.class, Solid.class);
        addObject(new HUD(width), width / 2, Settings.HUD_Y);
    }
    //Em cada frame verifica a pausa, o desbloqueio do poder e o fim de jogo.
    @Override
    public void act(){
        checkPause();
        checkPowerUnlock();
        checkRestart();
    }
    //Devolve o nível seguinte (null no último nível)
    public abstract Level nextLevel();
    //Cria uma cópia nova do mesmo nível (usada para repetir o nível).
    public abstract Level createNew();
    //Número do nível, usado ao guardar o jogo.
    public abstract int getLevelNumber();
    //Adiciona o Zig e a Zag ao nível
    public void addAgents(int zigX, int zagX, int y)
    {
        addObject(new Agent(Settings.AGENT_LIVES, Settings.AGENT_SPEED, Settings.AGENT_JUMP,
                Settings.ZIG_LEFT, Settings.ZIG_RIGHT, Settings.ZIG_JUMP, Settings.ZIG_POWER,
                Settings.ZIG_ATTACK, Settings.ZIG_ATTACK_FRAMES, Settings.ZIG_PREFIX), zigX, y);

        addObject(new Agent(Settings.AGENT_LIVES, Settings.AGENT_SPEED, Settings.AGENT_JUMP,
                Settings.ZAG_LEFT, Settings.ZAG_RIGHT, Settings.ZAG_JUMP, Settings.ZAG_POWER,
                Settings.ZAG_ATTACK, Settings.ZAG_ATTACK_FRAMES, Settings.ZAG_PREFIX), zagX, y);
    }
    //Lê o mapa CSV e cria os blocos sólidos e as armadilhas do nível. 
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
    //Passa ao nível seguinte, somando o bónus de nível concluído. 
    public void advanceLevel() {
        Level next = nextLevel();

        if (next != null) {
            addLevelBonus();
            next.setStartingScore(score);
            Greenfoot.setWorld(next);
        }
    }
    //Soma pontos à pontuação da equipa.
    public void addScore(int points){
        score += points;
    }

    public int getScore(){
        return score;
    }
    //Define a pontuação com que o nível começa (guardada para o caso de se repetir)
    public void setStartingScore(int points)
    {
        score = points;
        startScore = points;
    }

    public int getStartScore()
    {
        return startScore;
    }
    //Pontos ganhos apenas neste nível.
    public int getLevelScore()
    {
        return score - startScore;
    }
    //Soma o bónus por concluir o nível.
    public void addLevelBonus(){
        addScore(Settings.LEVEL_COMPLETE_BONUS);
    }

    public boolean isPowerUnlocked(){
        return powerUnlocked;
    }
    //Pontuação necessária para desbloquear o poder
    public int getUnlockScore() { return Settings.DEFAULT_UNLOCK_SCORE; }
    //Nome do poder do nível, mostrado no HUD.
    public String getPowerName() { return Settings.POWER_NONE; }
    //Efeito do poder
    public void usePower(Agent user) { }
    //Indica se os agentes podem atacar neste nível.
    public boolean allowsAttack()
    {
        return false;
    }
    //Indica se a tecla do poder levanta um escudo neste nível.
    public boolean usesShield()
    {
        return false;
    }
    //True enquanto houver uma caixa de diálogo no ecrã.
    public boolean isDialogueActive(){
        return !getObjects(Dialogue.class).isEmpty();
    }
    //Abre o ecrã de pausa quando a tecla é carregada
    private void checkPause()
    {
        boolean pauseDown = Greenfoot.isKeyDown(Settings.KEY_PAUSE);
        if (pauseDown && !pauseKeyHeld) {
            Greenfoot.setWorld(new PauseWorld(this));
        }
        pauseKeyHeld = pauseDown;
    }
    //Se algum agente ficou sem vidas, mostra o ecrã de fim de jogo.
    private void checkRestart()
    {
        for (Agent agent : getObjects(Agent.class)) {
            if (agent.getLives() <= 0) {
                Greenfoot.setWorld(new GameOverWorld(this));
                return;
            }
        }
    }
    //Desbloqueia o poder quando a pontuação chega ao valor exigido
    private void checkPowerUnlock(){
        if (!powerUnlocked && score >= getUnlockScore()) {
            powerUnlocked = true;
        }
    }
}

