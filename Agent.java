import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Agent extends Character
{
   
    private String leftKey, rightKey, jumpKey;
    private int speed;
    private int jumpStrength;
    private boolean jumpKeyReleased = false;
    private int airDrift=0;
    private static final int DRIFT_SPEED=3;

    
    private GreenfootImage[] walkRight, walkLeft;
    private GreenfootImage[] jumpRight, jumpLeft;
    
    private int frame = 0;
    private int frameDelay = 0;        
    private boolean facingRight = true;
    
    public Agent (int lives, int speed, int jumpStrength, String leftKey, String rightKey, String jumpKey, String prefix){
        super(lives);
        this.speed = speed;
        this.jumpStrength = jumpStrength;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.jumpKey = jumpKey;
        
        walkRight = loadFrames(prefix + "_walk", 4);
        walkLeft  = mirrorAll(walkRight);
        jumpRight = loadFrames(prefix + "_jump", 7);
        jumpLeft  = mirrorAll(jumpRight);
        
        setImage(walkRight[0]);
    }
    
    public void act()
    {
        checkKeys();
        checkFall();
        updateInvulnerability();
        
    }
    
    private void  checkKeys(){
        boolean moving = false;
        boolean jumpDown = Greenfoot.isKeyDown(jumpKey);
        boolean grounded = onGround();
        
        if (Greenfoot.isKeyDown(leftKey) &&  !touchingWall(-getImage().getWidth()/2 - speed)){
            
            setLocation(getX()-speed, getY());
            facingRight = false;
            moving = true;
        }
         if (Greenfoot.isKeyDown(rightKey) && !touchingWall(getImage().getWidth()/2 + speed)){
            
            setLocation(getX()+speed, getY());
            facingRight = true;
            moving = true;
        }
        
        if (grounded && jumpDown) {
        
            jump(jumpStrength);
            airDrift = facingRight ? DRIFT_SPEED : -DRIFT_SPEED;
            jumpKeyReleased = false;
        }
        else if (grounded) {
           
            setGliding(false);
            jumpKeyReleased = false;
            airDrift = 0;
        }
        else {
           
            if (!jumpDown) {
                jumpKeyReleased = true;
            }
            setGliding(jumpDown && jumpKeyReleased && getVerticalSpeed() > 0);
        
            if (!moving) {
                driftInAir();
            }
        }

        animate(moving);
        
    }
    
        private void driftInAir()
    {
        if (airDrift == 0) {
            return;
        }
        int edge = (airDrift > 0 ? 1 : -1) * getImage().getWidth() / 2;
        if (!touchingWall(edge + airDrift)) {
          setLocation(getX() + airDrift, getY());
        }
    }
    
    private void animate(boolean moving) {
        if (!onGround()) {
            int index = jumpFrameIndex();
            setImage(facingRight ? jumpRight[index] : jumpLeft[index]);
            return;
        }
        if (!moving) {
            setImage(facingRight ? walkRight[0] : walkLeft[0]);   
            return;
        }
        frameDelay++;
        if (frameDelay >= 5) {            
            frameDelay = 0;
            frame = (frame + 1) % walkRight.length;
        }
        setImage(facingRight ? walkRight[frame] : walkLeft[frame]);
    }
    private int jumpFrameIndex()
    {
        int last = jumpRight.length - 1;
        int index = (getVerticalSpeed() + jumpStrength) * last / (2 * jumpStrength);
        return Math.max(0, Math.min(last, index));
    }
            
    private GreenfootImage[] loadFrames(String prefix, int count) {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++) {
            frames[i] = new GreenfootImage(prefix + (i + 1) + ".png");
        }
        return frames;
    }
    
    private GreenfootImage[] mirrorAll(GreenfootImage[] frames) {
        GreenfootImage[] flipped = new GreenfootImage[frames.length];
        for (int i = 0; i < frames.length; i++) {
            flipped[i] = new GreenfootImage(frames[i]);   
            flipped[i].mirrorHorizontally();
        }
        return flipped;
    }
       
}
