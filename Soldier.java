import greenfoot.*;
/**
 * O soldado patrulha como os outros inimigos, mas quando vê um agente à sua
 * frente pára, aponta e dispara. A visão pode ser reduzida com o poder lightsOff.
 */
public class Soldier extends Enemy implements Neutralizable
{
    private int sightRange;
    private GreenfootImage[] shootRight, shootLeft;
    private boolean aiming;
    private int shootAnimTimer;
    private int aimTimer;
    //Cria o soldado e carrega as imagens de andar e de disparar
    public Soldier(){
        super(Settings.SOLDIER_LIVES, Settings.SOLDIER_SPEED);
        sightRange = Settings.SOLDIER_SIGHT;
        aiming = false;
        shootAnimTimer = 0;
        aimTimer = Settings.SOLDIER_AIM_TIME;
        setWalkFrames(Settings.SOLDIER_WALK_PREFIX, Settings.SOLDIER_WALK_FRAMES);
        shootRight = Frames.load(Settings.SOLDIER_ATTACK_PREFIX, Settings.SOLDIER_ATTACK_FRAMES);
        shootLeft = Frames.mirror(shootRight);
    }
    //Sem alvo patrulha, com um agente à vista vira-se, aponta e dispara
    @Override
    public void patrol(){
        Agent target = findTarget();

        if (target == null) {
            aiming = false;
            aimTimer = Settings.SOLDIER_AIM_TIME;
            super.patrol();
            return;
        }
        aiming = true;
        setDirection(target.getX() > getX() ? Settings.RIGHT : Settings.LEFT);

        if (aimTimer > 0) {
            aimTimer--;
        } else {
            shoot();
            aimTimer = Settings.SOLDIER_RELOAD_TIME;
            shootAnimTimer = Settings.SOLDIER_SHOOT_ANIM_TIME;
        }
    }
    //Usa a animação de disparo quando está a apontar, senão a de andar
    @Override
    public void animate()
    {
        if (!aiming) {
            super.animate();
            return;
        }

        int index = 0;
        if (shootAnimTimer > 0) {
            int progress = Settings.SOLDIER_SHOOT_ANIM_TIME - shootAnimTimer;
            index = Math.min(shootRight.length - 1, progress * shootRight.length / Settings.SOLDIER_SHOOT_ANIM_TIME);
            shootAnimTimer--;
        }
        setImage(getDirection() > 0 ? shootRight[index] : shootLeft[index]);
    }
    //Adormece o soldado durante algum tempo
    @Override
    public void neutralize(){
        sleep(Settings.SOLDIER_SLEEP_TIME);
    }
    //Muda o alcance da visão ( quando as luzes se apagam)
    public void setSightRange(int range)
    {
        sightRange = range;
    }
    //Repõe o alcance normal da visão
    public void resetSightRange()
    {
        sightRange = Settings.SOLDIER_SIGHT;
    }
    //Procura o agente mais próximo, dentro do alcance e à mesma altura
    private Agent findTarget(){
        Agent closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (Agent agent : getObjectsInRange(sightRange, Agent.class)) {
            double distance = Math.abs(agent.getX() - getX());
            if (Math.abs(agent.getY() - getY()) <= Settings.SOLDIER_SIGHT_HEIGHT && distance < closestDistance) {
                closest = agent;
                closestDistance = distance;
            }
        }
        return closest;
    }
    //Cria uma bala à frente do soldado e toca o som do disparo
    private void shoot(){
        int dir = getDirection();
        int startX = getX() + dir * (getHalfWidth() + Settings.SOLDIER_BULLET_OFFSET);
        getWorld().addObject(new Bullet(dir), startX, getY());
        Greenfoot.playSound(Settings.SOUND_SHOT);
    }
}