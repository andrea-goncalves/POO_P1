import greenfoot.*;
/**
 * Caixa de diálogo com o nome de quem fala e o texto. O jogador avança
 * com a tecla ENTER e a caixa desaparece depois da última fala.
 */
public class Dialogue extends Actor {
    private String[] names;
    private String[] texts;
    private int index;
    private boolean enterHeld;
    private Font nameFont;
    private Font textFont;
    //Cria o diálogo e mostra a primeira fala
    public Dialogue(String[] names, String[] texts){
        this.names = names;
        this.texts = texts;
        index = 0;
        enterHeld = true;
        nameFont = new Font(Settings.DIALOGUE_NAME_SIZE);
        textFont = new Font(Settings.DIALOGUE_TEXT_SIZE);
        showDialogue();
    }
    //Avança para a fala seguinte quando se carrega em ENTER; remove a caixa no fim
    @Override
    public void act(){
        boolean enter = Greenfoot.isKeyDown(Settings.KEY_NEXT_DIALOGUE);

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
    // desenha a fala atual
    private void showDialogue(){
        GreenfootImage image = new GreenfootImage(Settings.DIALOGUE_WIDTH, Settings.DIALOGUE_HEIGHT);

        image.setColor(Color.BLACK);
        image.fill();

        image.setColor(Color.YELLOW);
        image.setFont(nameFont);
        image.drawString(names[index], Settings.DIALOGUE_TEXT_X, Settings.DIALOGUE_NAME_Y);

        image.setColor(Color.WHITE);
        image.setFont(textFont);
        image.drawString(texts[index], Settings.DIALOGUE_TEXT_X, Settings.DIALOGUE_TEXT_Y);

        image.drawString(Settings.DIALOGUE_HINT, Settings.DIALOGUE_HINT_X, Settings.DIALOGUE_HINT_Y);

        setImage(image);
    }
}