import greenfoot.*;

public class BossProjectile extends Actor
{
    private int direction;
    private static final int SPEED = 7;

    public BossProjectile(int direction)
    {
        this.direction = direction;

        GreenfootImage img = new GreenfootImage(20, 20);
        img.setColor(Color.RED);
        img.fillOval(0, 0, 20, 20);

        setImage(img);
    }

    public void act()
    {
        setLocation(getX() + direction * SPEED,getY());

        Agent player = (Agent) getOneIntersectingObject(Agent.class);

        if (player != null) {
            player.takeDamage();
            getWorld().removeObject(this);
            return;
        }

        if (isAtEdge() || isTouching(Solid.class)) {
            getWorld().removeObject(this);
        }
    }
}