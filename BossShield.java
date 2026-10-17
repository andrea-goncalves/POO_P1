import greenfoot.*;

public class BossShield extends Solid
{
    private int health = 100;
    private Boss boss;

    public BossShield(Boss boss)
    {
        super(32, 120);
        this.boss = boss;

        GreenfootImage img = new GreenfootImage(32, 120);
        img.setColor(Color.BLUE);
        img.fill();

        setImage(img);
    }

    public void takeDamage(int damage)
    {
        health -= damage;

        if (health <= 0) {
            boss.breakShield();
            getWorld().removeObject(this);
        }
    }
}