import greenfoot.*;

//Porta que bloqueia a passagem. Quando é ativada abre durante algum tempo e depois volta a fechar.

public class Door extends Actor implements Activatable {
    private boolean isOpen;
    private int openDurationFrames;
    private int timer;
    private Solid collision;
    //Cria a porta
    public Door(double openTimeSeconds) {
        openDurationFrames = (int) (openTimeSeconds * Settings.FRAMES_PER_SECOND);
        setImage(Settings.DOOR_IMAGE);
        isOpen = false;
        timer = 0;
        collision = null;
    }
    //Quando a porta é adicionada, cria o bloco que bloqueia a passagem
    @Override
    public void addedToWorld(World world) {
        collision = new Solid(getImage().getWidth(), getImage().getHeight());

        world.addObject(collision, getX(), getY());
    }
    //Conta o tempo e fecha a porta quando acaba, se ninguém estiver na passagem
    @Override
    public void act() {
        if (isOpen && openDurationFrames > 0) {
            timer--;

            if (timer <= 0 && getIntersectingObjects(GameCharacter.class).isEmpty()) {
                closeDoor();
            }
        }
    }
    //Ativar uma porta é abri-la
    @Override
    public void activate()
    {
        openDoor();
    }

    public boolean isOpen() {
        return isOpen;
    }
    //Abre a porta: remove o bloco que bloqueia e torna a imagem transparente
    public void openDoor() {
        if (!isOpen) {
            isOpen = true;

            if (openDurationFrames > 0) {
                timer = openDurationFrames;
            }

            getWorld().removeObject(collision);
            getImage().setTransparency(Settings.TRANSPARENCY_FADED);
        }
    }
    //Fecha a porta: volta a colocar o bloco e a imagem opaca
    public void closeDoor() {
        if (isOpen) {
            isOpen = false;

            getWorld().addObject(collision, getX(), getY());
            getImage().setTransparency(Settings.TRANSPARENCY_OPAQUE);
        }
    }
}