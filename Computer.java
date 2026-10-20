
import greenfoot.*;

public class Computer extends Actor
{
    private Door[] portasAssociadas;
    private MovingPlatform[] plataformasAssociadas;
    private boolean teclaPressionada;

    public Computer(Door[] portas, MovingPlatform[] plataformas) {
        portasAssociadas = portas;
        plataformasAssociadas = plataformas;
        teclaPressionada = false;
        setImage("computer.png");
    }
    public Computer(Door... portas) {
    this(portas, new MovingPlatform[]{});
    }

    public void act() {
        checkInteraction();
    }

    private void checkInteraction() {
        Agent character = (Agent) getOneIntersectingObject(Agent.class);

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
