import greenfoot.*;

public class BossLevel extends Level
{
    public BossLevel()
    {
        super(960, 540);

        addObject(
            new Agent(5, 6, 16, "a", "d", "w", "s", "zig"),
            100, 450
        );

        addObject(
            new Agent(5, 6, 16,
                "left", "right", "up", "down", "zag"),
            160, 450
        );

        // Chão
        for (int x = 16; x < 960; x += TILE_SIZE) {
            addObject(new Solid(), x, 524);
        }

        // Boss
        addObject(new Boss(), 750, 448);
    }

    public void bossDefeated()
    {
        showText("BOSS DERROTADO!", 480, 250);
        Greenfoot.stop();
    }

    @Override
    protected Level createNew()
    {
        return new BossLevel();
    }

    @Override
    public int getUnlockScore()
    {
        return 40;
    }

    @Override
    public String getPowerName()
    {
        return "None";
    }

    @Override
    public void usePower(Agent user)
    {
    }
    @Override
    protected Level nextLevel()
    {
    return null;
    }
}