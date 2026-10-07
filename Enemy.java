import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class Enemy extends Character
{
    
    private int speed;
    private int direction = 1;
    
    public Enemy(int lives, int speed)
    {
        super(lives);              
        this.speed = speed;
    }
    public void act()
    {
        patrol();
        checkFall();
        updateInvulnerability();
        damageAgentOnContact();
    }
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
    }
    
    //public abstract void neutralize();//still need to work on this
    
}
