import greenfoot.*;

public class Boss extends Enemy {
    private static final int STUN_DURATION = 180;
    private static final int STUN_FRAME_DELAY = 8;
    private static final int SHOOT_INTERVAL = 90;
    private static final int SHOOT_FRAME_DELAY = 5;
    private static final int MAX_HEALTH = 300;
    private static final int SHIELD_DISTANCE = 9;
    private static final int SHIELD_HEIGHT = 160;
    private static final int SHIELD_WIDTH = 8;
    private static final int GROUND_Y = 235;

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

    public Boss(){
        super(1, 0);
        stunned = false;
        stunTimer = 0; 
        shootTimer = SHOOT_INTERVAL; 
        health = MAX_HEALTH; 
        shield = null; 
        shooting = false; 
        shootFrame = 0; 
        shootFrameDelay = 0;
        
        idleImage = new GreenfootImage("general_walk1.png");
        shootFrames = loadFrames("general_attack", 8);
        stunFrames = loadFrames("general_stum", 7);

        setImage(idleImage);
        setDirection(-1);
    }

    @Override
    protected void addedToWorld(World world){
        createShield();
    }

    @Override
    public void act(){
        if (!getWorld().getObjects(Dialogue.class).isEmpty()) {
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
            shootTimer = SHOOT_INTERVAL;
        }
    }

    private int getShieldY(){
        return GROUND_Y - SHIELD_HEIGHT / 2;
    }

    private int getShieldX(){
        return getX() - SHIELD_DISTANCE;
    }

    private void createShield(){
        if (getWorld() == null) return;

        shield = new BossShield(this, SHIELD_WIDTH, SHIELD_HEIGHT);
        getWorld().addObject(shield, getShieldX(), getShieldY());
    }

    private void updateShieldPosition(){
        if (shield != null && shield.getWorld() != null) {
            shield.setLocation(getShieldX(), getShieldY());
        }
    }

    public void breakShield(){
        stunned = true;
        stunTimer = STUN_DURATION;

        shooting = false;
        shootFrame = 0;
        shootFrameDelay = 0;

        stunFrame = 0;
        stunFrameDelay = 0;

        shield = null;
        setImage(stunFrames[0]);
    }

    private void animateStun(){
        setImage(stunFrames[stunFrame]);

        if (stunFrame < stunFrames.length - 1) {
            stunFrameDelay++;

            if (stunFrameDelay >= STUN_FRAME_DELAY) {
                stunFrameDelay = 0;
                stunFrame++;
                setImage(stunFrames[stunFrame]);
            }
        }
    }

    @Override
    public void takeDamage(int damage){
        if (!stunned || health <= 0) return;

        health = Math.max(0, health - damage);

        if (health == 0) {
            ((BossLevel) getWorld()).bossDefeated();
        }
    }

    private void recover(){
        //pushPlayers();

        stunned = false;
        stunTimer = 0;
        stunFrame = 0;
        stunFrameDelay = 0;

        shootTimer = SHOOT_INTERVAL;

        setImage(idleImage);
        createShield();
    }
    
   public int getMaxHealth(){
    return MAX_HEALTH;
}

    private void startShooting(){
        shooting = true;
        shootFrame = 0;
        shootFrameDelay = 0;

        setImage(shootFrames[0]);
    }

    private void animateShooting(){
        if (!shooting) return;

        setImage(shootFrames[shootFrame]);

        if (shootFrame == 4 && shootFrameDelay == 0) {
            shoot();
        }

        shootFrameDelay++;

        if (shootFrameDelay >= SHOOT_FRAME_DELAY) {
            shootFrameDelay = 0;
            shootFrame++;

            if (shootFrame >= shootFrames.length) {
                shooting = false;
                shootFrame = 0;
                setImage(idleImage);
            }
        }
    }

    private void shoot(){
        if (getWorld() == null) return;

        int direction = getDirection();
        int bossHeight = idleImage.getHeight();
        int projectileHeight = 20;

        int bottomY = GROUND_Y - projectileHeight / 2;
        int middleY = getY();
        int topY = getY() - bossHeight / 2 + projectileHeight / 2;

        int spawnX = getShieldX() - SHIELD_WIDTH / 2 - 20;
        int choice = Greenfoot.getRandomNumber(3);
        int spawnY;

        if (choice == 0) {
            spawnY = bottomY;
        }
        else if (choice == 1) {
            spawnY = middleY;
        }
        else {
            spawnY = topY;
        }

        getWorld().addObject(
            new Bullet(direction, 7, 20),
            spawnX,
            spawnY
        );
        Greenfoot.playSound("blast.wav");
    }

    @Override
    public void neutralize(){
    }

    public int getHealth(){
        return health;
    }

    public boolean isStunned(){
        return stunned;
    }
}

