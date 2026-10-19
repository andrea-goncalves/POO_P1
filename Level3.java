
import greenfoot.*;

public class Level3 extends Level {
    private static final int LIGHTS_OFF_TIME = 300;
    private static final int SHORT_SIGHT = 100;

    private int lightsOffTimer = 0;
    private DarknessOverlay overlay;

    public Level3(){
        super(960, 540);

        carregarColisoes("Colisao_fase3.csv");
        setBackground("fase3.png");

        addObject(new Agent(5, 6, 16, "a", "d", "w", "s", "zig"), 25, 135);
        addObject(new Agent(5, 6, 16, "left", "right", "up", "down", "zag"), 30, 135);

        MovingPlatform plataform1 = new MovingPlatform(0, -4 * TILE_SIZE);
        MovingPlatform plataform2 = new MovingPlatform(0, 3 * TILE_SIZE);

        Door door1 = new Door(5);

        addObject(door1, 800, 30);

        addObject(plataform1, 350, 490);
        addObject(plataform2, 448, 302);

        addObject(new Computador(new Door[]{door1}, new MovingPlatform[]{plataform1}), 642, 40);
        addObject(new Computador(new Door[]{door1}, new MovingPlatform[]{plataform2}), 585, 455);

        addObject(new CrushingWall(2), 320, 35);
        addObject(new CrushingWall(1), 832, 207);
        addObject(new CrushingWall(1), 704, 207);
        addObject(new CrushingWall(1), 576, 207);
        addObject(new CrushingWall(1), 96, 207);
        addObject(new CrushingWall(1), 768, 357);
        addObject(new CrushingWall(1), 640, 357);
        addObject(new CrushingWall(1), 512, 357);

        addObject(new Soldier(), 455, 135);
        addObject(new Soldier(), 900, 50);
        
        addObject(new Clue(20),930,50);
    }

    public void fall(){
    }

    @Override
    public void usePower(Agent user){
        //Greenfoot.playSound("switch.wav");

        lightsOffTimer = LIGHTS_OFF_TIME;

        if (overlay == null) {
            overlay = new DarknessOverlay(getWidth(), getHeight());
            addObject(overlay, getWidth() / 2, getHeight() / 2);
        }

        for (Soldier soldier : getObjects(Soldier.class)) {
            soldier.setSightRange(SHORT_SIGHT);
        }
    }

    @Override
    public void act(){
        super.act();

        if (lightsOffTimer > 0) {
            lightsOffTimer--;

            if (lightsOffTimer == 0) {
                lightsOn();
            }
        }
    }

    private void lightsOn(){
        if (overlay != null) {
            removeObject(overlay);
            overlay = null;
        }

        for (Soldier soldier : getObjects(Soldier.class)) {
            soldier.resetSightRange();
        }
    }

    @Override
    protected Level createNew(){
        return new Level3();
    }

    @Override
    public int getUnlockScore(){
        return 60;
    }

    @Override
    public String getPowerName(){
        return "Lights Off";
    }

    @Override
    protected Level nextLevel(){
        return new BossLevel();
    }
}
