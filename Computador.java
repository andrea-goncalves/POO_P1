
import greenfoot.*;

public class Computador extends Actor
{
    private Door[] portasAssociadas;
    private MovingPlatform[] plataformasAssociadas;
    private boolean teclaPressionada = false;

    public Computador(Door[] portas, MovingPlatform[] plataformas) {
        portasAssociadas = portas;
        plataformasAssociadas = plataformas;
        setImage("computer.png");
    }
    public Computador(Door... portas) {
    this(portas, new MovingPlatform[]{});
    }

    public void act() {
        checkInteraction();
    }

    private void checkInteraction() {
        Character character =
            (Character) getOneIntersectingObject(Character.class);

        boolean teclaE = Greenfoot.isKeyDown("e");

        if (character != null && teclaE && !teclaPressionada) {

            for (Door porta : portasAssociadas) {
                if (porta != null) {
                    porta.openDoor();
                }
            }

            for (MovingPlatform plataforma : plataformasAssociadas) {
                if (plataforma != null) {
                    plataforma.activate();
                }
            }
        }

        teclaPressionada = teclaE;
    }
}
