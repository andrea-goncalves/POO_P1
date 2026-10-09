import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Bullet extends Actor
{
    private static final int SPEED = 10;
    private int direction;
    
    public Bullet(int direction){
        this.direction = direction;

        GreenfootImage img = new GreenfootImage(14, 5);
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
    }
    public void act()
    {
        setLocation(getX() + direction * SPEED, getY());

        Agent agent = (Agent) getOneIntersectingObject(Agent.class);
        if (agent != null) {
            agent.takeDamage();            
            getWorld().removeObject(this);
            return;                         
        }

        if (isTouching(Wall.class) || isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
