
import greenfoot.*;

public class Boss extends Enemy {
    private static final int STUN_DURATION = 180;
    private static final int SHOOT_INTERVAL = 90;
    private static final int SHOOT_FRAME_DELAY = 5;
    private static final int MAX_HEALTH = 300;

    private static final int SHIELD_DISTANCE = 42;
    private static final int SHIELD_HEIGHT = 220;
    private static final int SHIELD_WIDTH = 32;
    private static final int GROUND_Y = 235;

    private boolean stunned = false;
    private int stunTimer = 0;
    private int shootTimer = SHOOT_INTERVAL;

    private int health = MAX_HEALTH;
    private BossShield shield;

    private GreenfootImage idleImage;
    private GreenfootImage[] shootFrames;

    private boolean shooting = false;
    private int shootFrame = 0;
    private int shootFrameDelay = 0;

    public Boss(){
        super(MAX_HEALTH, 0);

        idleImage = new GreenfootImage("general_walk1.png");
        shootFrames = loadFrames("general_attack", 8);

        setImage(idleImage);
        setDirection(-1);
    }

    @Override
    protected void addedToWorld(World world){
        createShield();
    }

    @Override
    public void act(){
        checkFall();
        updateShieldPosition();

        if (stunned) {
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

        shield = new BossShield(this);
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

        shield = null;
        setImage(idleImage);
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
        pushPlayers();

        stunned = false;
        shootTimer = SHOOT_INTERVAL;

        setImage(idleImage);
        createShield();
    }

    private void pushPlayers(){
        for (Agent player : getWorld().getObjects(Agent.class)) {
            int direction = player.getX() < getX() ? -1 : 1;
            int newX = player.getX() + direction * 100;
            int halfWidth = player.getImage().getWidth() / 2;

            newX = Math.max(halfWidth,Math.min(getWorld().getWidth() - halfWidth, newX));

            player.setLocation(newX, player.getY());
        }
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
            new BossProjectile(direction),
            spawnX,
            spawnY
        );
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
