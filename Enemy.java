import greenfoot.*;
/**
 * Classe base dos inimigos. Patrulha de um lado para o outro, vira nas
 * paredes, anima a caminhada, pode adormecer e tira uma vida ao agente que tocar.
 */
public abstract class Enemy extends GameCharacter
{
 
    private GreenfootImage[] walkRight, walkLeft;
    private int frame;
    private int frameDelay;
    private int speed;
    private int direction;
    private int sleepTimer;
    private boolean moving;

    // Cria o inimigo com vidas e velocidade; começa a andar para a direita
    public Enemy(int lives, int speed)
    {
        super(lives);
        this.speed = speed;
        walkRight = null;
        walkLeft = null;
        frame = 0;
        frameDelay = 0;
        direction = Settings.RIGHT;
        sleepTimer = 0;
        moving = false;
    }
    //Se não estiver neeutralizado/a dormir, patrulha, anima e ataca por contacto
    @Override
    public void update()
    {
        if (sleepTimer > 0) {
            sleepTimer--;
            checkFall();
            if (sleepTimer == 0) {
                onWake();
            }
            return;
        }
        moving = false;
        patrol();
        animate();
        checkFall();
        damageAgentOnContact();
    }
    //Carrega as frames da caminhada
    public void setWalkFrames(String prefix, int count){
        walkRight = Frames.load(prefix, count);
        walkLeft = Frames.mirror(walkRight);
        setImage(walkRight[0]);
    }
    //Escolhe a imagem parado ou a andar
    public void animate(){
        if (walkRight == null) {
            return;
        }
        if (!moving) {
            setImage(direction > 0 ? walkRight[0] : walkLeft[0]);
            frame = 0;
            frameDelay = 0;
            return;
        }
        frameDelay++;
        if (frameDelay >= Settings.ENEMY_ANIMATION_DELAY) {
            frameDelay = 0;
            frame = (frame + 1) % walkRight.length;
        }
        setImage(direction > 0 ? walkRight[frame] : walkLeft[frame]);
    }
    //Anda na direção atual e vira ao bater numa parede ou no limite do mundo
    public void patrol()
    {
        if (touchingWall(direction * (getHalfWidth() + speed))) {
            direction = -direction;
        }
        setLocation(getX() + direction * speed, getY());
        moving = true;
        if (keepInsideWorld()) {
            direction = -direction;
        }
    }
    //é chamado quando o inimigo fere um agente, as subclasses podem reagir (ex: ladrar)
    public void onHitAgent(){ }
    //Faz o inimigo adormecer durante o número de frames indicado
    public void sleep(int acts){
        sleepTimer = acts;
    }
    //é chamado quando o inimigo acorda
    public void onWake(){ }

    public int getDirection(){
        return direction;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }
    //Tira uma vida ao agente que estiver a tocar no inimigo
    private void damageAgentOnContact(){
        Agent agent = (Agent) getOneIntersectingObject(Agent.class);
        if (agent != null && !agent.isInvulnerable()) {
            agent.takeDamage();
            onHitAgent();
        }
    }
}