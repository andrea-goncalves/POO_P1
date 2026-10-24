
import greenfoot.*;

public class Dialogue extends Actor {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 130;
    private static final Font NAME_FONT = new Font(22);
    private static final Font TEXT_FONT = new Font(17);
    private static final String HINT = "ENTER para continuar";
        
    private String[] names;
    private String[] texts;
    private int index;
    private boolean enterHeld;

    public Dialogue(String[] names, String[] texts){
        this.names = names;
        this.texts = texts;
        index = 0;
        enterHeld = true;
        showDialogue();
    }
    @Override
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
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(Color.BLACK);
        image.fill();

        image.setColor(Color.YELLOW);
        image.setFont(NAME_FONT);
        image.drawString(names[index], 20, 30);

        image.setColor(Color.WHITE);
        image.setFont(TEXT_FONT);
        image.drawString(texts[index], 20, 75);

        image.drawString(HINT, 680, 115);

        setImage(image);
    }
}
