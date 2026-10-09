import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class Enemy extends Character
{
    
    private int speed;
    private int direction = 1;
    private int sleepTimer = 0;
    
    public Enemy(int lives, int speed)
    {
        super(lives);              
        this.speed = speed;
    }
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
        patrol();
        checkFall();
        updateInvulnerability();
        damageAgentOnContact();
    }
    protected void sleep(int acts){
        sleepTimer = acts;
    }
    
    protected void onWake(){}
    
    public abstract void neutralize();
    private void damageAgentOnContact()
    {
        Agent agent = (Agent) getOneIntersectingObject(Agent.class);
        if (agent != null) {
            agent.takeDamage();
        }
    }
    
    protected void patrol()
    {
        if (touchingWall(direction * (getImage().getWidth()/2 + speed))) {
            direction = -direction;
        }
        setLocation(getX() + direction * speed, getY());
        if (keepInsideWorld()) {
            direction = -direction;
        }
    }
    
       
}
