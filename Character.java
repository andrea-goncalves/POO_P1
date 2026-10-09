import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class Character extends Actor
{
   
    
    private double vSpeed=0;
    private final double ACCELARATION = 1;
    private boolean gliding=false;
    private static final double GLIDE_FACTOR=0.25;
    private static final double GLIDE_MAX_SPEED=3;
    
    private int lives;
    protected int invulnerableTimer = 0;  
    private static final int INVULNERABLE_TIME = 60;
    
    public Character(int lives)
    {
        this.lives = lives;
    }
    
    public void act(){}
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
    
        lives -= amount;
        invulnerableTimer = INVULNERABLE_TIME;
    
       if (lives <= 0) {
            lives = 0;
            onNoLives();
       }
    }
    protected void onNoLives(){
        //alterar quando tenha os niveis
    }
    public boolean isInvulnerable(){
        return invulnerableTimer > 0;
    }
    protected void updateInvulnerability()
    {
        if (invulnerableTimer > 0) 
            invulnerableTimer--;
    }
    protected void applyBlink(){
        if (!isInvulnerable()) {
            return;
        }
    
        double progress = (double) (INVULNERABLE_TIME - invulnerableTimer) / INVULNERABLE_TIME;
        int transparency = (int) (177 + 78 * Math.cos(4 * Math.PI * progress));
    
        GreenfootImage copy = new GreenfootImage(getImage());  
        copy.setTransparency(transparency);
        setImage(copy);
    }
    protected void jump(int strength)
    {
        vSpeed = -strength;
    }
     protected void setGliding(boolean gliding) { 
         this.gliding = gliding; 
    }
    protected void fall(){
        setLocation(getX(), getY()+ (int) vSpeed);
        if (gliding && vSpeed>0){
            vSpeed +=ACCELARATION* GLIDE_FACTOR;
            if (vSpeed>GLIDE_MAX_SPEED){
                vSpeed= GLIDE_MAX_SPEED;
            }
        }else {
            vSpeed += ACCELARATION;
        }
        
    }
    protected void checkFall(){
        if (vSpeed < 0 && hitHead()) {
            while (hitHead()) {
                setLocation(getX(), getY() + 1);   
            }
            vSpeed = 0;                            
        }
        
        if (onGround()&& vSpeed >= 0){
            while (getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class) != null){
                setLocation(getX(), getY()-1);
            }
            vSpeed=0;
        }
        else {
            fall();
        }
    }
    protected int getVerticalSpeed()
    {
        return (int)vSpeed;
    }
    protected boolean onGround(){
        Actor under= getOneObjectAtOffset(0, getImage().getHeight() /2+1, Ground.class );
        return under!=null;
    }
    protected boolean hitHead(){
        return getOneObjectAtOffset(0, -getImage().getHeight() / 2, Ceiling.class) != null;
    }
    protected boolean touchingWall(int dx){
        return getOneObjectAtOffset(dx, 0, Wall.class) != null;
    }
    protected boolean keepInsideWorld(){
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
    protected GreenfootImage[] loadFrames(String prefix, int count) {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++) {
            frames[i] = new GreenfootImage(prefix + (i + 1) + ".png");
        }
        return frames;
    }
    protected GreenfootImage[] mirrorAll(GreenfootImage[] frames) {
        GreenfootImage[] flipped = new GreenfootImage[frames.length];
        for (int i = 0; i < frames.length; i++) {
            flipped[i] = new GreenfootImage(frames[i]);   
            flipped[i].mirrorHorizontally();
        }
        return flipped;
    }
    
}

