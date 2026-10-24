import greenfoot.*;

public class Soldier extends Enemy implements Neutralizable
{
    private int sightRange;
    private GreenfootImage[] shootRight, shootLeft;
    private boolean aiming;
    private int shootAnimTimer;
    private int aimTimer;

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

    @Override
    public void neutralize(){
        sleep(Settings.SOLDIER_SLEEP_TIME);
    }

    public void setSightRange(int range)
    {
        sightRange = range;
    }

    public void resetSightRange()
    {
        sightRange = Settings.SOLDIER_SIGHT;
    }

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

    private void shoot(){
        int dir = getDirection();
        int startX = getX() + dir * (getHalfWidth() + Settings.SOLDIER_BULLET_OFFSET);
        getWorld().addObject(new Bullet(dir), startX, getY());
        Greenfoot.playSound(Settings.SOUND_SHOT);
    }
}