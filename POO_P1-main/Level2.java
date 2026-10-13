import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Level2 extends Level
{
    private static final int LIGHTS_OFF_TIME = 300;    
    private static final int SHORT_SIGHT = 100;        
    private int lightsOffTimer = 0;
    private DarknessOverlay overlay;
    public Level2(){
        super(960, 540);

        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(Color.DARK_GRAY);
        background.fill();
        setBackground(background);
        
        addGround(0, 500, 960, 40);
        addWall(0, 380, 20, 120);
        addWall(940, 380, 20, 120);

        addObject(new Soldier(), 700, 470);
        //addObject(new Soldier(), 700, 470);
        addObject(new Agent(5, 6, 16, "a", "d", "w", "s", "zig"), 100, 470);
        addObject(new Clue(), 200, 470);
        addObject(new Clue(), 500, 470);
        addObject(new Clue(20), 850, 470);
    
    }
    @Override
        public void usePower(Agent user){
            // Greenfoot.playSound("switch.wav");
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
        removeObject(overlay);
        overlay = null;
        for (Soldier soldier : getObjects(Soldier.class)) {
            soldier.resetSightRange();
        }
    }
    @Override
    protected Level createNew(){
        return new Level2();
    }
    @Override
    public int getUnlockScore(){
        return 40;
    }
    @Override
    public String getPowerName(){
        return "Lights off";
    }
        
}
