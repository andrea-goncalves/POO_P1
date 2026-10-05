import greenfoot.*;  
/**
 * Write a description of class Dog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dog extends Enemy 
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Dog
     */
    public Dog(){
        super(1, 3);
        GreenfootImage img = new GreenfootImage(50, 30);
        img.setColor(Color.ORANGE);
        img.fill();
        setImage(img);
    }
    @Override
    public void neutralize(){
        // TODO: fall asleep (stop moving for a few seconds)
    }
    
}
