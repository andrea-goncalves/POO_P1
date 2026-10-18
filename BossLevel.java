import greenfoot.*;

public class BossLevel extends Level
{
    public BossLevel()
    {
        super(960, 540);
        carregarColisoes("Colisao_boss.csv");
        setBackground("faseBoss.png");

        addObject(
            new Agent(5, 6, 16, "a", "d", "w", "s", "zig"),100, 223);

        addObject(
            new Agent(5, 6, 16,"left", "right", "up", "down", "zag"),160, 223);
        
        addObject(new Boss(), 920, 223);
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
        return 80;
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