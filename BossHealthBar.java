import greenfoot.*;
/**
 * Barra de vida do boss, desenhada no fundo do ecrã.
 * Só é redesenhada quando a vida do chefe muda.
 */
public class BossHealthBar extends Actor {
    private Boss boss;
    private int lastHealth;
    //Cria a barra ligada ao chefe e desenha-a pela primeira vez
    public BossHealthBar(Boss boss){
        this.boss = boss;
        lastHealth = Settings.BAR_UNSET;
        updateBar();
    }
    //Redesenha a barra se a vida do chefe mudou
    @Override
    public void act(){
        if (boss.getHealth() != lastHealth) {
            updateBar();
        }
    }
    //Desenha a moldura, o fundo e a parte vermelha proporcional à vida
    private void updateBar(){
        int health = boss.getHealth();
        lastHealth = health;

        double percentage = (double) health / boss.getMaxHealth();
        int filledWidth = (int) (Settings.BAR_WIDTH * percentage);
        int fullWidth = Settings.BAR_WIDTH + 2 * Settings.BAR_BORDER;
        int fullHeight = Settings.BAR_HEIGHT + 2 * Settings.BAR_BORDER;

        GreenfootImage image = new GreenfootImage(fullWidth, fullHeight);

        image.setColor(Settings.BAR_BORDER_COLOR);
        image.fillRect(0, 0, fullWidth, fullHeight);

        image.setColor(Settings.BAR_BACK_COLOR);
        image.fillRect(Settings.BAR_BORDER, Settings.BAR_BORDER, Settings.BAR_WIDTH, Settings.BAR_HEIGHT);

        image.setColor(Settings.BAR_FILL_COLOR);
        image.fillRect(Settings.BAR_BORDER, Settings.BAR_BORDER, filledWidth, Settings.BAR_HEIGHT);

        setImage(image);
    }
}