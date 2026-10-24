
import greenfoot.*;

public class BossShield extends Solid {
    private static final int MAX_HEALTH = 100;
    private int health;
    private Boss boss;

    public BossShield(Boss boss, int width, int height){
        super(width, height);
        this.boss = boss;
        health = MAX_HEALTH;
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
