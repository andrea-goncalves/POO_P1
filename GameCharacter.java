import greenfoot.*;
/**
 * Classe base de todas as personagens (agentes e inimigos). Trata da física
 * (gravidade, salto, planar, colisões com o chão e as paredes), das vidas
 * e do efeito de piscar depois de levar dano.
 */
public abstract class GameCharacter extends Actor
{
    private double vSpeed;
    private boolean gliding;
    private int lives;
    private int invulnerableTimer;
    //Cria a personagem com o número de vidas indicado
    public GameCharacter(int lives)
    {
        this.lives = lives;
        vSpeed = 0;
        gliding = false;
        invulnerableTimer = 0;
    }
    //Atualiza a invulnerabilidade, chama o comportamento próprio e faz piscar
    @Override
    public final void act()
    {
        updateInvulnerability();
        update();
        applyBlink();
    }
    //Comportamento de cada frame, definido por cada subclasse
    public abstract void update();

    public int getLives()
    {
        return lives;
    }
    //Tira uma vida
    public void takeDamage(){
        takeDamage(1);
    }
    //Tira vidas, a não ser que a personagem esteja invulnerável
    public void takeDamage(int amount){
        if (invulnerableTimer > 0) {
            return;
        }
        lives = Math.max(0, lives - amount);
        invulnerableTimer = Settings.INVULNERABLE_TIME;
    }

    public boolean isInvulnerable(){
        return invulnerableTimer > 0;
    }
    //Começa um salto com a força indicada
    public void jump(int strength)
    {
        vSpeed = -strength;
    }
    //Ativa ou desativa o planar (queda mais lenta)
    public void setGliding(boolean gliding) {
        this.gliding = gliding;
    }
    //Trata do teto, do chão e da queda: pára no chão ou aplica a gravidade
    public void checkFall(){
        if (vSpeed < 0 && hitHead()) {
            int attempts = 0;
            while (hitHead() && attempts < Settings.MAX_PUSH) {
                setLocation(getX(), getY() + 1);
                attempts++;
            }
            vSpeed = 0;
        }

        if (onGround() && vSpeed >= 0){
            int attempts = 0;
            while (getOneObjectAtOffset(0, getHalfHeight(), Solid.class) != null && attempts < Settings.MAX_PUSH){
                setLocation(getX(), getY() - 1);
                attempts++;
            }
            vSpeed = 0;
        }
        else {
            fall();
        }
    }

    public int getVerticalSpeed()
    {
        return (int) vSpeed;
    }
    //True se houver um bloco sólido mesmo debaixo dos pés
    public boolean onGround(){
        return getOneObjectAtOffset(0, getHalfHeight() + Settings.GROUND_CHECK_DISTANCE, Solid.class) != null;
    }
    //True se houver um bloco sólido à distância dx na horizontal
    public boolean touchingWall(int dx){
        return getOneObjectAtOffset(dx, 0, Solid.class) != null;
    }
    //Mantém a personagem dentro do mundo
    public boolean keepInsideWorld(){
        int minX = getHalfWidth();
        int maxX = getWorld().getWidth() - getHalfWidth();

        int x = Math.max(minX, Math.min(maxX, getX()));
        if (x != getX()) {
            setLocation(x, getY());
            return true;
        }
        return false;
    }
    //Metade da largura da imagem atual
    public int getHalfWidth()
    {
        return getImage().getWidth() / 2;
    }
    //Metade da altura da imagem atual
    public int getHalfHeight()
    {
        return getImage().getHeight() / 2;
    }
    //Posição vertical dos pés
    public int getFeetY()
    {
        return getY() + getHalfHeight();
    }
    //Conta o tempo de invulnerabilidade
    private void updateInvulnerability()
    {
        if (invulnerableTimer > 0) {
            invulnerableTimer--;
        }
    }
    //Faz a imagem piscar (varia a transparência)
    private void applyBlink(){
        if (!isInvulnerable()) {
            return;
        }

        double progress = (double) (Settings.INVULNERABLE_TIME - invulnerableTimer) / Settings.INVULNERABLE_TIME;
        int transparency = (int) (Settings.BLINK_BASE
                + Settings.BLINK_AMPLITUDE * Math.cos(2 * Math.PI * Settings.BLINK_CYCLES * progress));

        GreenfootImage copy = new GreenfootImage(getImage());
        copy.setTransparency(transparency);
        setImage(copy);
    }
    //trata das quedas; ao planar a gravidade/velocidade da queda é menor
    private void fall(){
        setLocation(getX(), getY() + (int) vSpeed);
        if (gliding && vSpeed > 0){
            vSpeed += Settings.ACCELERATION * Settings.GLIDE_FACTOR;
            if (vSpeed > Settings.GLIDE_MAX_SPEED){
                vSpeed = Settings.GLIDE_MAX_SPEED;
            }
        } else {
            vSpeed += Settings.ACCELERATION;
        }
        if (vSpeed > Settings.MAX_FALL_SPEED) {
            vSpeed = Settings.MAX_FALL_SPEED;
        }
    }
    //True se a cabeça bate num bloco sólido
    private boolean hitHead(){
        return getOneObjectAtOffset(0, -getHalfHeight(), Solid.class) != null;
    }
}
