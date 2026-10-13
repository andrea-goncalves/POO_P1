import greenfoot.*;
public class Soldier extends Enemy 
{
    private static final int NORMAL_SIGHT = 300;
    private int sightRange = NORMAL_SIGHT;
    
    private static final int SIGHT_HEIGHT = 60;     
    private static final int AIM_TIME = 30;         
    private static final int RELOAD_TIME = 90;
    
    private GreenfootImage[] shootRight, shootLeft;
    private boolean aiming = false;
    private int shootAnimTimer = 0;
    private static final int SHOOT_ANIM_TIME = 15;
    private int aimTimer = AIM_TIME;
    public Soldier(){
        super(1, 2);                               
        setWalkFrames("soldier_walk", 4);           
        shootRight = loadFrames("soldier_shoot", 9);            
        shootLeft = mirrorAll(shootRight);
    }
    @Override
    protected void patrol(){
        Agent target = findTarget();

        if (target == null) {
            aiming = false;
            aimTimer = AIM_TIME;
            super.patrol(); 
            return;
        }
        aiming = true;
        setDirection(target.getX() > getX() ? 1 : -1);   // face the agent

        if (aimTimer > 0) {
            aimTimer--;
        } else {
            shoot();
            aimTimer = RELOAD_TIME;
            shootAnimTimer = SHOOT_ANIM_TIME;
        }
    }
    public void setSightRange(int range)
    {
        sightRange = range;
    }
    public void resetSightRange()
    {
        sightRange = NORMAL_SIGHT;
    }
    private Agent findTarget(){
        Agent closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (Object obj : getObjectsInRange(sightRange, Agent.class)) {
            Agent agent = (Agent) obj;
            double distance = Math.abs(agent.getX() - getX());
            if (Math.abs(agent.getY() - getY()) <= SIGHT_HEIGHT && distance < closestDistance) {
                closest = agent;
                closestDistance = distance;
            }
        }
        return closest;
    }
    private void shoot(){
        int dir = getDirection();
        int startX = getX() + dir * (getImage().getWidth() / 2 + 10);
        getWorld().addObject(new Bullet(dir), startX, getY());
        // Greenfoot.playSound("shot.wav");
    }
    @Override
    public void neutralize(){
        sleep(300);                                
    }
    @Override
    protected void animate()
    {
        if (!aiming) {
            super.animate();
            return;
        }
    
        int index = 0;                             
        if (shootAnimTimer > 0) {
            int progress = SHOOT_ANIM_TIME - shootAnimTimer;
            index = Math.min(shootRight.length - 1, progress * shootRight.length / SHOOT_ANIM_TIME);
            shootAnimTimer--;
        }
        setImage(getDirection() > 0 ? shootRight[index] : shootLeft[index]);
    }
}    
