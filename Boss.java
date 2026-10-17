import greenfoot.*;

public class Boss extends Actor
{
    private int health = 300;

    private boolean stunned = false;
    private int stunTimer = 0;

    private int shootTimer = 90;

    private BossShield shield;

    public Boss()
    {
        GreenfootImage img = new GreenfootImage(80, 120);
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
    }

    protected void addedToWorld(World world)
    {
        createShield();
    }

    public void act()
    {
        if (stunned) {
            stunTimer--;

            if (stunTimer <= 0) {
                recover();
            }

            return;
        }

        shootTimer--;

        if (shootTimer <= 0) {
            shoot();
            shootTimer = 90;
        }
    }

    private void createShield()
    {
        shield = new BossShield(this);

        getWorld().addObject(
            shield,
            getX() - 60,
            getY()
        );
    }

    public void breakShield()
    {
        stunned = true;
        stunTimer = 180;
    }

    public void takeDamage(int damage)
    {
        if (!stunned) return;

        health -= damage;

        if (health <= 0) {
            ((BossLevel) getWorld()).bossDefeated();
        }
    }

    private void recover()
    {
        stunned = false;

        pushPlayers();
        createShield();
    }

    private void pushPlayers()
    {
        for (Agent player : getWorld().getObjects(Agent.class)) {
            int pushDirection =
                player.getX() < getX() ? -1 : 1;

            int newX = player.getX() + pushDirection * 100;

            newX = Math.max(
                player.getImage().getWidth() / 2,
                Math.min(
                    getWorld().getWidth()
                        - player.getImage().getWidth() / 2,
                    newX
                )
            );

            player.setLocation(newX, player.getY());
        }
    }

    private void shoot()
    {
        getWorld().addObject(
            new BossProjectile(-1),
            getX() - 45,
            getY()
        );
    }
}