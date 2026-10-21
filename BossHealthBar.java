import greenfoot.*;

public class BossHealthBar extends Actor {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 20;

    private Boss boss;

    public BossHealthBar(Boss boss){
        this.boss = boss;
        updateBar();
    }

    public void act(){
        updateBar();
    }

    private void updateBar(){
        int health = boss.getHealth();
        int maxHealth = boss.getMaxHealth();

        double percentage = (double) health / maxHealth;
        int filledWidth = (int)(WIDTH * percentage);

        GreenfootImage image = new GreenfootImage(WIDTH + 4, HEIGHT + 4);

        image.setColor(Color.BLACK);
        image.fillRect(0, 0, WIDTH + 4, HEIGHT + 4);

        image.setColor(Color.DARK_GRAY);
        image.fillRect(2, 2, WIDTH, HEIGHT);

        image.setColor(Color.RED);
        image.fillRect(2, 2, filledWidth, HEIGHT);

        setImage(image);
    }
}
