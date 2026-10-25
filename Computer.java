
import greenfoot.*;
/**
 * Computador interativo. Quando um agente está em cima e carrega na tecla
 * de interação, ativa todos os objetos ligados a ele (portas e plataformas)
 */
public class Computer extends Actor
{
    private Activatable[] targets;
    private boolean keyHeld;
    //Recebe um ou mais objetos ativáveis
    public Computer(Activatable... targets)
    {
        this.targets = targets;
        keyHeld = false;
        setImage(Settings.COMPUTER_IMAGE);
    }
    //Ativa os objetos quando a tecla é carregada
    @Override
    public void act()
    {
        boolean interactDown = Greenfoot.isKeyDown(Settings.KEY_INTERACT);

        if (isTouching(Agent.class) && interactDown && !keyHeld) {
            for (Activatable target : targets) {
                target.activate();
            }
        }
        keyHeld = interactDown;
    }
}