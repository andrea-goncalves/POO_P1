import greenfoot.*;  
/**
 * Write a description of class Dog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dog extends Enemy 
{
    
    public Dog(){
        super(1, 3);
        setWalkFrames("dog_walk", 4);
        

    }
        
    @Override
    public void neutralize()
    {
        sleep(300);                       
        getImage().setTransparency(100);  
    }
    
    @Override
    protected void onWake()
    {
        getImage().setTransparency(255);
    }
    
    @Override
    protected void onHitAgent(){
        Greenfoot.playSound("bark.wav");
    }
}
