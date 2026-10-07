import greenfoot.*;  
/**
 * Write a description of class Dog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dog extends Enemy 
{
    

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
    
    //public void neutralize(){
        // falta fall asleep (stop moving for a few seconds)
    //}
    
}
