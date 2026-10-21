
import greenfoot.*;

public class Dialogue extends Actor {
    private String[] names;
    private String[] texts;
    private int index = 0;
    private boolean enterHeld = true;

    public Dialogue(String[] names, String[] texts){
        this.names = names;
        this.texts = texts;
        showDialogue();
    }

    public void act(){
        boolean enter = Greenfoot.isKeyDown("enter");

        if (enter && !enterHeld) {
            index++;

            if (index == texts.length) {
                getWorld().removeObject(this);
                return;
            }

            showDialogue();
        }

        enterHeld = enter;
    }

    private void showDialogue(){
        GreenfootImage image = new GreenfootImage(900, 130);

        image.setColor(Color.BLACK);
        image.fill();

        image.setColor(Color.YELLOW);
        image.setFont(new Font(22));
        image.drawString(names[index], 20, 30);

        image.setColor(Color.WHITE);
        image.setFont(new Font(17));
        image.drawString(texts[index], 20, 75);

        image.drawString("ENTER para continuar", 680, 115);

        setImage(image);
    }
}
