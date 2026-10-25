import greenfoot.*;

/**
 *  O boss (general Von Kross) dispara projéteis em alturas diferentes e protege-se com um
 * escudo. Quando o escudo parte, fica atordoado e pode ser atacado.
 */
public class Boss extends Enemy {

    private boolean stunned;
    private int stunTimer;
    private int shootTimer;
    private int health;
    private BossShield shield;

    private GreenfootImage idleImage;
    private GreenfootImage[] shootFrames;
    private GreenfootImage[] stunFrames;

    private boolean shooting;
    private int shootFrame;
    private int shootFrameDelay;
    private int stunFrame;
    private int stunFrameDelay;
    //Cria o chefe, carrega as imagens e vira-o para a esquerda
    public Boss(){
        super(Settings.BOSS_LIVES, Settings.BOSS_SPEED);
        stunned = false;
        stunTimer = 0;
        shootTimer = Settings.BOSS_SHOOT_INTERVAL;
        health = Settings.BOSS_MAX_HEALTH;
        shield = null;
        shooting = false;
        shootFrame = 0;
        shootFrameDelay = 0;
        stunFrame = 0;
        stunFrameDelay = 0;

        idleImage = new GreenfootImage(Settings.BOSS_IDLE_IMAGE);
        shootFrames = Frames.load(Settings.BOSS_ATTACK_PREFIX, Settings.BOSS_ATTACK_FRAMES);
        stunFrames = Frames.load(Settings.BOSS_STUN_PREFIX, Settings.BOSS_STUN_FRAMES);

        setImage(idleImage);
        setDirection(Settings.LEFT);
    }
    //Quando o chefe entra no mundo, cria o escudo.
    @Override
    public void addedToWorld(World world){
        createShield();
    }
    //espera nos diálogos, recupera do atordoamento ou dispara
    @Override
    public void update(){
        if (((Level) getWorld()).isDialogueActive()) {
            return;
        }
        updateShieldPosition();

        if (stunned) {
            animateStun();
            stunTimer--;

            if (stunTimer <= 0) {
                recover();
            }
            return;
        }

        if (shootTimer > 0) {
            shootTimer--;
        }

        if (shooting) {
            animateShooting();
        }
        else if (shootTimer <= 0) {
            startShooting();
            shootTimer = Settings.BOSS_SHOOT_INTERVAL;
        }
    }
    //O boss só sofre dano quando está atordoado; ao chegar a zero o nível acaba
    @Override
    public void takeDamage(int damage){
        if (!stunned || health <= 0) return;

        health = Math.max(0, health - damage);

        if (health == 0) {
            ((BossLevel) getWorld()).bossDefeated();
        }
    }
    // É chamado quando o escudo parte e o boss fica atordoado
    public void breakShield(){
        stunned = true;
        stunTimer = Settings.BOSS_STUN_DURATION;

        shooting = false;
        shootFrame = 0;
        shootFrameDelay = 0;

        stunFrame = 0;
        stunFrameDelay = 0;

        shield = null;
        setImage(stunFrames[0]);
    }

    public int getMaxHealth(){
        return Settings.BOSS_MAX_HEALTH;
    }

    public int getHealth(){
        return health;
    }

    public boolean isStunned(){
        return stunned;
    }
    //Posição vertical do escudo
    private int getShieldY(){
        return Settings.BOSS_GROUND_Y - Settings.BOSS_SHIELD_HEIGHT / 2;
    }
    //Posição horizontal do escudo
    private int getShieldX(){
        return getX() - Settings.BOSS_SHIELD_DISTANCE;
    }
    //Cria um escudo novo à frente do boss
    private void createShield(){
        if (getWorld() == null) return;

        shield = new BossShield(this, Settings.BOSS_SHIELD_WIDTH, Settings.BOSS_SHIELD_HEIGHT);
        getWorld().addObject(shield, getShieldX(), getShieldY());
    }
    //Mantém o escudo na posição certa
    private void updateShieldPosition(){
        if (shield != null && shield.getWorld() != null) {
            shield.setLocation(getShieldX(), getShieldY());
        }
    }
    //Mostra a animação de atordoamento
    private void animateStun(){
        setImage(stunFrames[stunFrame]);

        if (stunFrame < stunFrames.length - 1) {
            stunFrameDelay++;

            if (stunFrameDelay >= Settings.BOSS_STUN_FRAME_DELAY) {
                stunFrameDelay = 0;
                stunFrame++;
                setImage(stunFrames[stunFrame]);
            }
        }
    }
    //Recupera do atordoamento e levanta um escudo novo
    private void recover(){
        stunned = false;
        stunTimer = 0;
        stunFrame = 0;
        stunFrameDelay = 0;

        shootTimer = Settings.BOSS_SHOOT_INTERVAL;

        setImage(idleImage);
        createShield();
    }
    //Começa a animação de disparo
    private void startShooting(){
        shooting = true;
        shootFrame = 0;
        shootFrameDelay = 0;

        setImage(shootFrames[0]);
    }
    //Avança a animação e dispara na frame certa
    private void animateShooting(){
        if (!shooting) return;

        setImage(shootFrames[shootFrame]);

        if (shootFrame == Settings.BOSS_SHOT_FRAME && shootFrameDelay == 0) {
            shoot();
        }

        shootFrameDelay++;

        if (shootFrameDelay >= Settings.BOSS_SHOOT_FRAME_DELAY) {
            shootFrameDelay = 0;
            shootFrame++;

            if (shootFrame >= shootFrames.length) {
                shooting = false;
                shootFrame = 0;
                setImage(idleImage);
            }
        }
    }
    //Cria um projétil numa altura aleatória e coloca o som do disparo
    private void shoot(){
        if (getWorld() == null) return;

        int spawnX = getShieldX() - Settings.BOSS_SHIELD_WIDTH / 2 - Settings.BOSS_BULLET_OFFSET;
        getWorld().addObject(
            new Bullet(getDirection(), Settings.BOSS_BULLET_SPEED, Settings.BOSS_PROJECTILE_SIZE),
            spawnX,
            randomShotHeight()
        );
        Greenfoot.playSound(Settings.SOUND_BOSS_SHOT);
    }
    //Escolhe ao acaso entre três alturas: baixa, média e alta
    private int randomShotHeight(){
        int half = Settings.BOSS_PROJECTILE_SIZE / 2;
        int[] heights = {
            Settings.BOSS_GROUND_Y - half,
            getY(),
            getY() - idleImage.getHeight() / 2 + half
        };
        return heights[Greenfoot.getRandomNumber(heights.length)];
    }
}
