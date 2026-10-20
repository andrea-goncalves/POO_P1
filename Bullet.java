import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Bullet extends Actor
{
    private static int speed;
    private int direction;
    
    public Bullet(int direction){
        this.direction = direction;
        this.speed=10;

        GreenfootImage img = new GreenfootImage(14, 5);
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
    }
    public Bullet(int direction, int speed, int diameter)
    {
        this.direction = direction;
        this.speed = speed;

        GreenfootImage img = new GreenfootImage(diameter, diameter);
        img.setColor(Color.RED);
        img.fillOval(0, 0, diameter, diameter);
        setImage(img);
    }
    public void act()
    {
        setLocation(getX() + direction * speed, getY());

        Agent agent = (Agent) getOneIntersectingObject(Agent.class);
        if (agent != null) {
            agent.takeDamage();            
            getWorld().removeObject(this);
            return;                         
        }
        
        if (isTouching(Solid.class) || isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
