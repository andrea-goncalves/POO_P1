import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class Character extends Actor
{
   
    private double vSpeed;
    private boolean gliding;
    private int lives;
    private int invulnerableTimer;  
    
    private static final double ACCELERATION = 1;
    private static final double GLIDE_FACTOR=0.25;
    private static final double GLIDE_MAX_SPEED=3;
    private static final double MAX_FALL_SPEED = 15;
    private static final int MAX_PUSH = 40;
    private static final int INVULNERABLE_TIME = 60;
    
    public Character(int lives)
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
       invulnerableTimer = INVULNERABLE_TIME;
    
    }
    public boolean isInvulnerable(){
        return invulnerableTimer > 0;
    }
    private void updateInvulnerability()
    {
        if (invulnerableTimer > 0) 
            invulnerableTimer--;
    }
    private void applyBlink(){
        if (!isInvulnerable()) {
            return;
        }
    
        double progress = (double) (INVULNERABLE_TIME - invulnerableTimer) / INVULNERABLE_TIME;
        int transparency = (int) (177 + 78 * Math.cos(4 * Math.PI * progress));
    
        GreenfootImage copy = new GreenfootImage(getImage());  
        copy.setTransparency(transparency);
        setImage(copy);
    }
    public void jump(int strength)
    {
        vSpeed = -strength;
    }
    public void setGliding(boolean gliding) { 
         this.gliding = gliding; 
    }
    private void fall(){
        setLocation(getX(), getY()+ (int) vSpeed);
        if (gliding && vSpeed>0){
            vSpeed +=ACCELERATION* GLIDE_FACTOR;
            if (vSpeed>GLIDE_MAX_SPEED){
                vSpeed= GLIDE_MAX_SPEED;
            }
        }else {
            vSpeed += ACCELERATION;
        }
        if (vSpeed > MAX_FALL_SPEED) {
            vSpeed = MAX_FALL_SPEED;
        }
    }
    public void checkFall(){
        if (vSpeed < 0 && hitHead()) {
            int attempts = 0;
            while (hitHead() && attempts < MAX_PUSH) {
                setLocation(getX(), getY() + 1);
                attempts++;
            }
            vSpeed = 0;
        }
    
        if (onGround() && vSpeed >= 0){
            int attempts = 0;
            while (getOneObjectAtOffset(0, getImage().getHeight()/2, Solid.class) != null && attempts < MAX_PUSH){
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
        return (int)vSpeed;
    }
    public boolean onGround(){
    return getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, Solid.class) != null;
    }
    private boolean hitHead(){
    return getOneObjectAtOffset(0, -getImage().getHeight() / 2, Solid.class) != null;
    }
    public boolean touchingWall(int dx){
    return getOneObjectAtOffset(dx, 0, Solid.class) != null;
    }
    public boolean keepInsideWorld(){
        int halfWidth = getImage().getWidth() / 2;
        int minX = halfWidth;
        int maxX = getWorld().getWidth() - halfWidth;
    
        int x = Math.max(minX, Math.min(maxX, getX()));
        if (x != getX()) {
            setLocation(x, getY());
            return true;
        }
        return false;
    }
    
    
}

