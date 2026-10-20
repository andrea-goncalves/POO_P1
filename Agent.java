import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Agent extends Character
{
   
    private String leftKey, rightKey, jumpKey, powerKey, attackKey;
    private int speed, jumpStrength;
    private boolean jumpKeyReleased,jumpKeyHeld, moving, facingRight ;
    private int airDrift;
    private int powerCooldown;
    private int frame, frameDelay;   
    private boolean attackKeyHeld, attacking;
    private int attackCooldown, attackFrame, attackFrameDelay;
    private GreenfootImage[] walkRight, walkLeft, jumpRight, jumpLeft, attackRight, attackLeft;

    private static final int DRIFT_SPEED=3;
    private static final int POWER_COOLDOWN = 240;
    private static final int ATTACK_COOLDOWN = 20;
    private static final int ATTACK_DAMAGE = 10;    
    private static final int ATTACK_FRAME_DELAY = 4;
    
    public Agent (int lives, int speed, int jumpStrength, String leftKey, String rightKey, String jumpKey, String powerKey, String prefix){
        super(lives);
        
        jumpKeyReleased = false;
        jumpKeyHeld = false;
        moving = false;
        facingRight = true;
        airDrift = 0;
        powerCooldown = 0;
        frame = 0;
        frameDelay = 0;
        attackKeyHeld = false;
        attacking = false;
        attackCooldown = 0;
        attackFrame = 0;
        attackFrameDelay = 0;
        
        this.speed = speed;
        this.jumpStrength = jumpStrength;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.jumpKey = jumpKey;
        this.powerKey = powerKey;
        if (prefix.equals("zig")) {
        attackKey = "f";
        }
        else {
            attackKey = "0";
        }
        
        walkRight = loadFrames(prefix + "_walk", 4);
        walkLeft  = mirrorAll(walkRight);
        jumpRight = loadFrames(prefix + "_jump", 7);
        jumpLeft  = mirrorAll(jumpRight);
        int attackFrameCount = prefix.equals("zig") ? 3 : 7;

        attackRight = loadFrames(prefix + "_attack", attackFrameCount);
        attackLeft = mirrorAll(attackRight);
        
        setImage(walkRight[0]);
        
    }
    
    public void act()
    {
        checkKeys();
        checkFall();
        animate(moving); 
        keepInsideWorld();
        updateInvulnerability();
        checkPower();
        applyBlink();
        checkAttack();
    }
    
    private void  checkKeys(){
        moving = false;
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
        if (grounded && jumpDown && !jumpKeyHeld) {
        
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
        jumpKeyHeld = jumpDown;
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
        private void checkPower(){
        if (powerCooldown > 0) {
            powerCooldown--;
            return;
        }
        Level level = (Level) getWorld();
        if (Greenfoot.isKeyDown(powerKey) && level.isPowerUnlocked()) {
            level.usePower(this);
            powerCooldown = POWER_COOLDOWN;
        }
    }
    private void animate(boolean moving){
        if (attacking) {
            animateAttack();
            return;
        }
    
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
    
    private void attack(){
        attacking = true;
        attackFrame = 0;
        attackFrameDelay = 0;
        hitTargets();
    }


    private void hitTargets()
    {
        int direction = facingRight ? 1 : -1;
        int attackOffset = direction * (getImage().getWidth() / 2 + 20);
    
        BossShield shield = (BossShield) getOneObjectAtOffset(attackOffset, 0, BossShield.class);
        if (shield != null) {
            shield.takeDamage(ATTACK_DAMAGE);
            return;                                   
        }
    
        Boss boss = (Boss) getOneObjectAtOffset(attackOffset, 0, Boss.class);
        if (boss != null) {
            boss.takeDamage(ATTACK_DAMAGE);
        }
    }
    
        private void checkAttack()
    {
        if (attackCooldown > 0) {
            attackCooldown--;
        }
    
        boolean attackDown = Greenfoot.isKeyDown(attackKey);
    
        if (attackDown && !attackKeyHeld
            && attackCooldown == 0
            && !attacking
            && ((Level) getWorld()).allowsAttack()) {
    
            attack();
            attackCooldown = ATTACK_COOLDOWN;
        }
    
        attackKeyHeld = attackDown;
    }
        private void animateAttack()
    {
        GreenfootImage[] frames =
            facingRight ? attackRight : attackLeft;
    
        setImage(frames[attackFrame]);
    
        attackFrameDelay++;
    
        if (attackFrameDelay >= ATTACK_FRAME_DELAY) {
            attackFrameDelay = 0;
            attackFrame++;
    
            if (attackFrame >= frames.length) {
                attacking = false;
                attackFrame = 0;
            }
        }
    }
             
}
