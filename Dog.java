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
        GreenfootImage img = new GreenfootImage(50, 30);
        img.setColor(Color.ORANGE);
        img.fill();
        setImage(img);
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
}
