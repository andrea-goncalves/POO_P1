
import greenfoot.*;

public class Computador extends Scenery {
    private Door[] portasAssociadas;
    private boolean teclaPressionada = false;

    // Recebe uma ou várias portas
    public Computador(Door... portas) {
        this.portasAssociadas = portas;
        setImage("computador.png");
    }

    public void act() {
        checkInteraction();
    }

    private void checkInteraction() {
        Character character =
            (Character) getOneIntersectingObject(Character.class);

        boolean teclaE = Greenfoot.isKeyDown("e");

        if (character != null && teclaE && !teclaPressionada) {

            // Abre todas as portas associadas
            for (Door porta : portasAssociadas) {
                if (porta != null) {
                    porta.openDoor();
                }
            }
        }

        teclaPressionada = teclaE;
    }
}
