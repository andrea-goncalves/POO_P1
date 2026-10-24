import greenfoot.*;

public class Agent extends GameCharacter {
    private String leftKey, rightKey, jumpKey, powerKey, attackKey;
    private int speed, jumpStrength;
    private boolean jumpKeyReleased, jumpKeyHeld, moving, facingRight;
    private int airDrift;
    private int powerCooldown;
    private int frame, frameDelay;
    private boolean attackKeyHeld, attacking;
    private int attackCooldown, attackFrame, attackFrameDelay;

    private GreenfootImage[] walkRight, walkLeft;
    private GreenfootImage[] jumpRight, jumpLeft;
    private GreenfootImage[] attackRight, attackLeft;

    private GreenfootImage shieldRight, shieldLeft;
    private AgentShield shield;
    private boolean shielding;

    public Agent(int lives, int speed, int jumpStrength, String leftKey, String rightKey, String jumpKey, String powerKey, String attackKey, int attackFrameCount, String prefix){
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
        shielding = false;
        shield = null;

        this.speed = speed;
        this.jumpStrength = jumpStrength;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.jumpKey = jumpKey;
        this.powerKey = powerKey;
        this.attackKey = attackKey;

        walkRight = Frames.load(prefix + Settings.WALK_SUFFIX, Settings.WALK_FRAMES);
        walkLeft = Frames.mirror(walkRight);

        jumpRight = Frames.load(prefix + Settings.JUMP_SUFFIX, Settings.JUMP_FRAMES);
        jumpLeft = Frames.mirror(jumpRight);

        attackRight = Frames.load(prefix + Settings.ATTACK_SUFFIX, attackFrameCount);
        attackLeft = Frames.mirror(attackRight);

        shieldRight = new GreenfootImage(prefix + Settings.SHIELD_SUFFIX);
        shieldLeft = new GreenfootImage(shieldRight);
        shieldLeft.mirrorHorizontally();

        setImage(walkRight[0]);
    }

    @Override
    public void update(){
        updateMovement();
        checkFall();
        checkPower();
        animate();
        keepInsideWorld();
        updateShieldPosition();
        checkAttack();
    }

    public boolean isShielding(){
        return shielding;
    }

    private void updateMovement(){
        if (canMove()) {
            checkKeys();
        }
        else {
            moving = false;
            jumpKeyHeld = Greenfoot.isKeyDown(jumpKey);
        }
    }

    private boolean canMove(){
        Level level = (Level) getWorld();
        if (level.isDialogueActive()) {
            return false;
        }
        if (level.usesShield() && Greenfoot.isKeyDown(powerKey)) {
            airDrift = 0;
            setGliding(false);
            return false;
        }

        return true;
    }

    private void checkKeys(){
        boolean grounded = onGround();
        moving = false;

        moveHorizontally();
        handleJump(grounded);
    }

    private void moveHorizontally(){
        if (Greenfoot.isKeyDown(leftKey)) {
            walk(Settings.LEFT);
        }
        if (Greenfoot.isKeyDown(rightKey)) {
            walk(Settings.RIGHT);
        }
    }

    private void walk(int direction){
        int edge = direction * (getHalfWidth() + speed);

        if (!touchingWall(edge)) {
            setLocation(getX() + direction * speed, getY());
            facingRight = direction > 0;
            moving = true;
        }
    }

    private void handleJump(boolean grounded){
        boolean jumpDown = Greenfoot.isKeyDown(jumpKey);

        if (grounded && jumpDown && !jumpKeyHeld) {
            startJump();
        }
        else if (grounded) {
            landed();
        }
        else {
            controlInAir(jumpDown);
        }
        jumpKeyHeld = jumpDown;
    }

    private void startJump(){
        jump(jumpStrength);
        airDrift = facingRight ? Settings.DRIFT_SPEED : -Settings.DRIFT_SPEED;
        jumpKeyReleased = false;
    }

    private void landed(){
        setGliding(false);
        jumpKeyReleased = false;
        airDrift = 0;
    }

    private void controlInAir(boolean jumpDown){
        if (!jumpDown) {
            jumpKeyReleased = true;
        }
        setGliding(jumpDown && jumpKeyReleased && getVerticalSpeed() > 0);

        if (!moving) {
            driftInAir();
        }
    }

    private void driftInAir(){
        if (airDrift == 0) {
            return;
        }

        int edge = (airDrift > 0 ? Settings.RIGHT : Settings.LEFT) * getHalfWidth();

        if (!touchingWall(edge + airDrift)) {
            setLocation(getX() + airDrift, getY());
        }
    }

    private void checkPower(){
        Level level = (Level) getWorld();

        if (level.usesShield()) {
            updateShield();
            return;
        }
        deactivateShield();
        usePowerIfReady(level);
    }

    private void updateShield(){
        if (Greenfoot.isKeyDown(powerKey) && !attacking) {
            activateShield();
        } else {
            deactivateShield();
        }
    }

    private void usePowerIfReady(Level level){
        if (powerCooldown > 0) {
            powerCooldown--;
            return;
        }
        if (Greenfoot.isKeyDown(powerKey) && level.isPowerUnlocked()) {
            level.usePower(this);
            powerCooldown = Settings.POWER_COOLDOWN;
        }
    }

    private void activateShield(){
        if (shielding) {
            return;
        }

        shielding = true;
        shield = new AgentShield();

        int direction = facingRight ? Settings.RIGHT : Settings.LEFT;

        getWorld().addObject(shield,getX() + direction * Settings.SHIELD_DISTANCE,getY());
    }

    private void deactivateShield(){
        shielding = false;

        if (shield != null) {
            if (shield.getWorld() != null) {
                shield.getWorld().removeObject(shield);
            }

            shield = null;
        }
    }

    private void updateShieldPosition(){
        if (!shielding || shield == null || shield.getWorld() == null) {
            return;
        }

        int direction = facingRight ? Settings.RIGHT : Settings.LEFT;

        shield.setLocation(
            getX() + direction * Settings.SHIELD_DISTANCE,
            getY()
        );
    }

    private void animate(){
        if (shielding) {
            setImage(facingRight ? shieldRight : shieldLeft);
            return;
        }

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

        if (frameDelay >= Settings.WALK_FRAME_DELAY) {
            frameDelay = 0;
            frame = (frame + 1) % walkRight.length;
        }

        setImage(facingRight ? walkRight[frame] : walkLeft[frame]);
    }

    private int jumpFrameIndex(){
        int last = jumpRight.length - 1;
        int index = (getVerticalSpeed() + jumpStrength) * last / (2 * jumpStrength);

        return Math.max(0, Math.min(last, index));
    }

    private void checkAttack(){
        if (attackCooldown > 0) {
            attackCooldown--;
        }

        boolean attackDown = Greenfoot.isKeyDown(attackKey);

        if (attackDown && !attackKeyHeld
        && attackCooldown == 0
        && !attacking
        && !shielding
        && ((Level) getWorld()).allowsAttack()) {

            attack();
            attackCooldown = Settings.ATTACK_COOLDOWN;
        }

        attackKeyHeld = attackDown;
    }

    private void attack(){
        attacking = true;
        attackFrame = 0;
        attackFrameDelay = 0;
        hitTargets();
    }

    private void hitTargets(){
        int direction = facingRight ? Settings.RIGHT : Settings.LEFT;
        int attackOffset = direction * (getHalfWidth() + Settings.ATTACK_REACH);

        BossShield bossShield = (BossShield) getOneObjectAtOffset(
                attackOffset, 0, BossShield.class
            );

        if (bossShield != null) {
            bossShield.takeDamage(Settings.ATTACK_DAMAGE);
            return;
        }

        Boss boss = (Boss) getOneObjectAtOffset(
                attackOffset, 0, Boss.class
            );

        if (boss != null) {
            boss.takeDamage(Settings.ATTACK_DAMAGE);
        }
    }

    private void animateAttack(){
        GreenfootImage[] frames = facingRight ? attackRight : attackLeft;

        setImage(frames[attackFrame]);

        attackFrameDelay++;

        if (attackFrameDelay >= Settings.ATTACK_FRAME_DELAY) {
            attackFrameDelay = 0;
            attackFrame++;

            if (attackFrame >= frames.length) {
                attacking = false;
                attackFrame = 0;
            }
        }
    }
}