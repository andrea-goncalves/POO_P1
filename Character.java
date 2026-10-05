import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Actor
{
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int vSpeed=0;//incremento do movimento vertical
    private final int acceleration = 2;
    private int jumpStrenght=18;
    
    
    protected boolean onGround(){
        Actor under= getOneObjectAtOffset(0, getImage().getHeight() /2+1, Ground.class );
        return under!=null;
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
