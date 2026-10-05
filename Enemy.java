import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Enemy extends Character
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
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
    
    public abstract void neutralize();//still need to work on this
    
}
