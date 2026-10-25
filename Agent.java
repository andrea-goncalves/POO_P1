import greenfoot.*;
/**
 * Agente controlado por um jogador (Zig ou Zag). Anda, salta, plana,
 * usa o poder do nível, ataca e levanta o escudo no combate com o chefe.
 * As teclas e as imagens são recebidas no construtor.
 */
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
    //Cria o agente. O prefixo ("zig" ou "zag") serve para carregar as imagens
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
    //Só lê as teclas se o agente se puder mexer
    private void updateMovement(){
        if (canMove()) {
            checkKeys();
        }
        else {
            moving = false;
            jumpKeyHeld = Greenfoot.isKeyDown(jumpKey);
        }
    }
    //O agente não se mexe durante um diálogo nem com o escudo levantado
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
    //Lê as teclas: andar e saltar
    private void checkKeys(){
        boolean grounded = onGround();
        moving = false;

        moveHorizontally();
        handleJump(grounded);
    }
    //Anda para a esquerda ou para a direita
    private void moveHorizontally(){
        if (Greenfoot.isKeyDown(leftKey)) {
            walk(Settings.LEFT);
        }
        if (Greenfoot.isKeyDown(rightKey)) {
            walk(Settings.RIGHT);
        }
    }
    //Dá um passo na direção indicada, se não houver parede
    private void walk(int direction){
        int edge = direction * (getHalfWidth() + speed);

        if (!touchingWall(edge)) {
            setLocation(getX() + direction * speed, getY());
            facingRight = direction > 0;
            moving = true;
        }
    }
    //Decide entre começar um salto,se está no chão ou no ar
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
    //Começa o salto e guarda o deslocamento horizontal no ar
    private void startJump(){
        jump(jumpStrength);
        airDrift = facingRight ? Settings.DRIFT_SPEED : -Settings.DRIFT_SPEED;
        jumpKeyReleased = false;
    }
    //Repõe o estado quando o agente está no chão
    private void landed(){
        setGliding(false);
        jumpKeyReleased = false;
        airDrift = 0;
    }
    //quando carregamos na tecla de salto, soltamos e voltamos a carregar,o agente fica a planar
    private void controlInAir(boolean jumpDown){
        if (!jumpDown) {
            jumpKeyReleased = true;
        }
        setGliding(jumpDown && jumpKeyReleased && getVerticalSpeed() > 0);

        if (!moving) {
            driftInAir();
        }
    }
    //Continua a mover-se na direção do salto, se não houver parede
    private void driftInAir(){
        if (airDrift == 0) {
            return;
        }

        int edge = (airDrift > 0 ? Settings.RIGHT : Settings.LEFT) * getHalfWidth();

        if (!touchingWall(edge + airDrift)) {
            setLocation(getX() + airDrift, getY());
        }
    }
    //No nível do chefe usa o escudo; nos outros usa o poder do nível
    private void checkPower(){
        Level level = (Level) getWorld();

        if (level.usesShield()) {
            updateShield();
            return;
        }
        deactivateShield();
        usePowerIfReady(level);
    }
    //coloca o escudo quando carregamos na tecla
    private void updateShield(){
        if (Greenfoot.isKeyDown(powerKey) && !attacking) {
            activateShield();
        } else {
            deactivateShield();
        }
    }
    //Usa o poder do nível se já estiver desbloqueado e não houver espera
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
    //Cria o escudo à frente do agente
    private void activateShield(){
        if (shielding) {
            return;
        }

        shielding = true;
        shield = new AgentShield();

        int direction = facingRight ? Settings.RIGHT : Settings.LEFT;

        getWorld().addObject(shield,getX() + direction * Settings.SHIELD_DISTANCE,getY());
    }
    //Remove o escudo do mundo
    private void deactivateShield(){
        shielding = false;

        if (shield != null) {
            if (shield.getWorld() != null) {
                shield.getWorld().removeObject(shield);
            }

            shield = null;
        }
    }
    //Mantém o escudo à frente do agente quando ele se mexe
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
    //Escolhe a imagem: escudo, ataque, salto, parado ou a andar
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
    //Escolhe a frame do salto conforme a velocidade vertical (a subir, no topo ou a descer)
    private int jumpFrameIndex(){
        int last = jumpRight.length - 1;
        int index = (getVerticalSpeed() + jumpStrength) * last / (2 * jumpStrength);

        return Math.max(0, Math.min(last, index));
    }
    //Começa um ataque quando a tecla é carregada, se o nível permitir e não houver espera
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
    //Inicia a animação de ataque e causa dano ao que estiver à frente
    private void attack(){
        attacking = true;
        attackFrame = 0;
        attackFrameDelay = 0;
        hitTargets();
    }
    //Causa dano ao escudo do boss ou, se não houver escudo, ao boss
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
    //Mostra as frames do ataque, uma de cada vez, até ao fim.
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