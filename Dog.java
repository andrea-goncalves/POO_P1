import greenfoot.*;
/**
 * Cão é um inimigo que patrulha e fere ao toque. Pode ser adormecido
 * pelo assobio e ladra quando fere um agente.
 */
public class Dog extends Enemy implements Neutralizable
{
    // Cria o cão com as suas vidas, velocidade e imagens
    public Dog(){
        super(Settings.DOG_LIVES, Settings.DOG_SPEED);
        setWalkFrames(Settings.DOG_WALK_PREFIX, Settings.DOG_WALK_FRAMES);
    }
    //Adormece o cão durante algum tempo e torna a imagem mais transparente
    @Override
    public void neutralize()
    {
        sleep(Settings.DOG_SLEEP_TIME);
        getImage().setTransparency(Settings.TRANSPARENCY_FADED);
    }
    //Ao acordar, a imagem volta a ser opaca
    @Override
    public void onWake()
    {
        getImage().setTransparency(Settings.TRANSPARENCY_OPAQUE);
    }
    //Ladra quando fere um agente
    @Override
    public void onHitAgent(){
        Greenfoot.playSound(Settings.SOUND_BARK);
    }
}