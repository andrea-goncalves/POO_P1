import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class Enemy extends Character
{
    private GreenfootImage[] walkRight, walkLeft;
    private int frame;
    private int frameDelay;
    private int speed;
    private int direction;
    private int sleepTimer;
    private boolean moving;
    
    private static final int ANIMATION_DELAY = 6; 
    
    public Enemy(int lives, int speed)
    {
        super(lives);              
        this.speed = speed;
        walkRight = null;
        walkLeft = null;
        frame = 0;
        frameDelay = 0;
        direction = 1;
        sleepTimer = 0;
        moving = false;
    }
    @Override
    public void act()
    {
        updateInvulnerability();
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
    protected void setWalkFrames(String prefix, int count){
        walkRight = loadFrames(prefix, count);
        walkLeft = mirrorAll(walkRight);
        setImage(walkRight[0]);
    }
    protected void animate(){
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
        if (frameDelay >= ANIMATION_DELAY) {
            frameDelay = 0;
            frame = (frame + 1) % walkRight.length;
        }
        setImage(direction > 0 ? walkRight[frame] : walkLeft[frame]);
    }
    protected void patrol()
    {
        if (touchingWall(direction * (getImage().getWidth()/2 + speed))) {
            direction = -direction;
        }
        setLocation(getX() + direction * speed, getY());
        moving = true;
        if (keepInsideWorld()) {
            direction = -direction;
        }
    }
    private void damageAgentOnContact(){
        Agent agent = (Agent) getOneIntersectingObject(Agent.class);
        if (agent != null && !agent.isInvulnerable()) {
            agent.takeDamage();
            onHitAgent();
        }
    }
    protected void onHitAgent(){}
    protected void sleep(int acts){
        sleepTimer = acts;
    }
    protected void onWake(){}
    public abstract void neutralize();
    protected int getDirection(){
        return direction;
    }
    protected void setDirection(int direction){
        this.direction = direction;
    }
}
