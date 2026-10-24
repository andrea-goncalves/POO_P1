import greenfoot.*;

public abstract class GameCharacter extends Actor
{
    private double vSpeed;
    private boolean gliding;
    private int lives;
    private int invulnerableTimer;

    public GameCharacter(int lives)
    {
        this.lives = lives;
        vSpeed = 0;
        gliding = false;
        invulnerableTimer = 0;
    }

    @Override
    public final void act()
    {
        updateInvulnerability();
        update();
        applyBlink();
    }

    public abstract void update();

    public int getLives()
    {
        return lives;
    }

    public void takeDamage(){
        takeDamage(1);
    }

    public void takeDamage(int amount){
        if (invulnerableTimer > 0) {
            return;
        }
        lives = Math.max(0, lives - amount);
        invulnerableTimer = Settings.INVULNERABLE_TIME;
    }

    public boolean isInvulnerable(){
        return invulnerableTimer > 0;
    }

    public void jump(int strength)
    {
        vSpeed = -strength;
    }

    public void setGliding(boolean gliding) {
        this.gliding = gliding;
    }

    public void checkFall(){
        if (vSpeed < 0 && hitHead()) {
            int attempts = 0;
            while (hitHead() && attempts < Settings.MAX_PUSH) {
                setLocation(getX(), getY() + 1);
                attempts++;
            }
            vSpeed = 0;
        }

        if (onGround() && vSpeed >= 0){
            int attempts = 0;
            while (getOneObjectAtOffset(0, getHalfHeight(), Solid.class) != null && attempts < Settings.MAX_PUSH){
                setLocation(getX(), getY() - 1);
                attempts++;
            }
            vSpeed = 0;
        }
        else {
            fall();
        }
    }

    public int getVerticalSpeed()
    {
        return (int) vSpeed;
    }

    public boolean onGround(){
        return getOneObjectAtOffset(0, getHalfHeight() + Settings.GROUND_CHECK_DISTANCE, Solid.class) != null;
    }

    public boolean touchingWall(int dx){
        return getOneObjectAtOffset(dx, 0, Solid.class) != null;
    }

    public boolean keepInsideWorld(){
        int minX = getHalfWidth();
        int maxX = getWorld().getWidth() - getHalfWidth();

        int x = Math.max(minX, Math.min(maxX, getX()));
        if (x != getX()) {
            setLocation(x, getY());
            return true;
        }
        return false;
    }

    public int getHalfWidth()
    {
        return getImage().getWidth() / 2;
    }

    public int getHalfHeight()
    {
        return getImage().getHeight() / 2;
    }

    public int getFeetY()
    {
        return getY() + getHalfHeight();
    }

    private void updateInvulnerability()
    {
        if (invulnerableTimer > 0) {
            invulnerableTimer--;
        }
    }

    private void applyBlink(){
        if (!isInvulnerable()) {
            return;
        }

        double progress = (double) (Settings.INVULNERABLE_TIME - invulnerableTimer) / Settings.INVULNERABLE_TIME;
        int transparency = (int) (Settings.BLINK_BASE
                + Settings.BLINK_AMPLITUDE * Math.cos(2 * Math.PI * Settings.BLINK_CYCLES * progress));

        GreenfootImage copy = new GreenfootImage(getImage());
        copy.setTransparency(transparency);
        setImage(copy);
    }

    private void fall(){
        setLocation(getX(), getY() + (int) vSpeed);
        if (gliding && vSpeed > 0){
            vSpeed += Settings.ACCELERATION * Settings.GLIDE_FACTOR;
            if (vSpeed > Settings.GLIDE_MAX_SPEED){
                vSpeed = Settings.GLIDE_MAX_SPEED;
            }
        } else {
            vSpeed += Settings.ACCELERATION;
        }
        if (vSpeed > Settings.MAX_FALL_SPEED) {
            vSpeed = Settings.MAX_FALL_SPEED;
        }
    }

    private boolean hitHead(){
        return getOneObjectAtOffset(0, -getHalfHeight(), Solid.class) != null;
    }
}
