import greenfoot.*;
/**
 * Escudo do boss. Tem vida própria: quando chega a zero, desaparece
 * e o boss fica atordoado.
 */
public class BossShield extends Solid {
    private int health;
    private Boss boss;
    //Cria o escudo com o tamanho indicado, ligado ao boss
    public BossShield(Boss boss, int width, int height){
        super(width, height);
        this.boss = boss;
        health = Settings.BOSS_SHIELD_HEALTH;
    }
    //Tira vida ao escudo; quando acaba, parte-o e atordoa o boss
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
