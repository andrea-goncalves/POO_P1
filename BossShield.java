
import greenfoot.*;

public class BossShield extends Solid {
    private static final int MAX_HEALTH = 100;
    private static final int SHIELD_WIDTH = 32;
    private static final int SHIELD_HEIGHT = 220;

    private int health = MAX_HEALTH;
    private Boss boss;

    public BossShield(Boss boss){
        super(SHIELD_WIDTH, SHIELD_HEIGHT);
        this.boss = boss;

        GreenfootImage image = new GreenfootImage(
            SHIELD_WIDTH,
            SHIELD_HEIGHT
        );

        image.clear();
        setImage(image);
    }

    public void takeDamage(int damage){
        if (health <= 0) return;

        health = Math.max(0, health - damage);

        if (health == 0) {
            boss.breakShield();

            if (getWorld() != null) {
                getWorld().removeObject(this);
            }
        }
    }

    public int getHealth(){
        return health;
    }
}
