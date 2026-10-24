import greenfoot.*;

public abstract class Enemy extends GameCharacter
{
 
    private GreenfootImage[] walkRight, walkLeft;
    private int frame;
    private int frameDelay;
    private int speed;
    private int direction;
    private int sleepTimer;
    private boolean moving;

    
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

    public void setWalkFrames(String prefix, int count){
        walkRight = Frames.load(prefix, count);
        walkLeft = Frames.mirror(walkRight);
        setImage(walkRight[0]);
    }

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

    public void onHitAgent(){ }

    public void sleep(int acts){
        sleepTimer = acts;
    }

    public void onWake(){ }

    public int getDirection(){
        return direction;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    private void damageAgentOnContact(){
        Agent agent = (Agent) getOneIntersectingObject(Agent.class);
        if (agent != null && !agent.isInvulnerable()) {
            agent.takeDamage();
            onHitAgent();
        }
    }
}