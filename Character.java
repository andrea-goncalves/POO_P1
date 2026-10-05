import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Character extends Actor
{
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int vSpeed=0;//incremento do movimento vertical
    private final int acceleration = 2;
        
    private int lives;
    private int invulnerableTimer = 0;  
    private static final int INVULNERABLE_TIME = 60;
    
    public Character(int lives)
    {
        this.lives = lives;
    }
    
    public void takeDamage()
    {
        takeDamage(1);
    }
    
    public void takeDamage(int amount)
    {
        if (invulnerableTimer > 0) return;
    
        lives -= amount;
        invulnerableTimer = INVULNERABLE_TIME;
    
        if (lives <= 0) {
            lives = 0;
            onNoLives();
        }
    }
    public int getLives()
    {
        return lives;
    }
    protected boolean onGround(){
        Actor under= getOneObjectAtOffset(0, getImage().getHeight() /2+1, Ground.class );
        return under!=null;
    }
    protected void updateInvulnerability()
    {
        if (invulnerableTimer > 0) invulnerableTimer--;
    }
    protected void onNoLives()
    {//alterar quando tenha os niveis
    }
    protected void checkFall(){
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
    
    protected void fall(){
        setLocation(getX(), getY()+ vSpeed);
        vSpeed +=acceleration;
    }
    
    protected void jump(int strength)
    {
        vSpeed = -strength;
    }
            
    protected boolean touchingWall(int dx){
        return getOneObjectAtOffset(dx, 0, Wall.class) != null;
    }
    
    protected int getVerticalSpeed()
    {
        return vSpeed;
    }
}
