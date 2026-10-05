import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Agent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Agent extends Character
{
    /**
     * Act - do whatever the Agent wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private String leftKey;
    private String rightKey;
    private String jumpKey;
    
    private int speed=7;
    private int jumpStrength = 18;
    
    private GreenfootImage[] walkRight, walkLeft;
    private GreenfootImage[] jumpRight, jumpLeft;
    
    private int frame = 0;
    private int frameDelay = 0;        
    private boolean facingRight = true;
    
    public Agent (String leftKey, String rightKey, String jumpKey, String prefix){
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
    }
    
    private void  checkKeys(){
        boolean moving = false;
        
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
        if(Greenfoot.isKeyDown(jumpKey)&& onGround()){
            jump(jumpStrength);

        }
        animate(moving);
        
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
