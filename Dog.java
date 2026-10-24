import greenfoot.*;

public class Dog extends Enemy implements Neutralizable
{
  
    public Dog(){
        super(Settings.DOG_LIVES, Settings.DOG_SPEED);
        setWalkFrames(Settings.DOG_WALK_PREFIX, Settings.DOG_WALK_FRAMES);
    }

    @Override
    public void neutralize()
    {
        sleep(Settings.DOG_SLEEP_TIME);
        getImage().setTransparency(Settings.TRANSPARENCY_FADED);
    }

    @Override
    public void onWake()
    {
        getImage().setTransparency(Settings.TRANSPARENCY_OPAQUE);
    }

    @Override
    public void onHitAgent(){
        Greenfoot.playSound(Settings.SOUND_BARK);
    }
}