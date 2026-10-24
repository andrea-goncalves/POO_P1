import greenfoot.*;

public class BossLevel extends Level {
    private boolean bossDead;
    public BossLevel(){
        super(960, 540);
        
        introDialogue();
               
        loadCollisions("Colisao_boss.csv");
        setBackground("faseBoss.png");
        
        addAgents(100, 160, 223);
        
        Boss boss = new Boss();
        addObject(boss, 894, 223);
        addObject(new BossHealthBar(boss), 480, 510);
    }

    public void bossDefeated(){
        bossDead = true;
        addLevelBonus();
        SaveGame.clear();
        victoryDialogue();
    }
    @Override
    public void act(){
        super.act();
        if (bossDead && !isDialogueActive()) {
            Greenfoot.setWorld(new WinWorld(getScore()));
        }
    }   
    
    @Override
    protected Level createNew(){
        return new BossLevel();
    }

    @Override
    public int getUnlockScore(){
        return 0;
    }

    @Override
    public String getPowerName(){
        return "Shield";
    }

    @Override
    public void usePower(Agent user){
    }

    @Override
    protected Level nextLevel(){
        return null;
    }

    @Override
    public boolean allowsAttack(){
        return true;
    }
    @Override
    public boolean usesShield(){
        return true;
    }
    @Override
    public int getLevelNumber(){
        return 4;
    }
    private void introDialogue(){
        String[] names = {"GENERAL VON KROSS", "ZAG", "ZIG"};

        String[] texts = {
            "Ratos de Zanarkin! Chegaram demasiado tarde!",
            "Essa bomba não vai destruir em Zanarkin!",
            "Rende-te, Von Kross! O teu palno fracassou!"
        };
        
        addObject(new Dialogue(names, texts), 480, 450);
    }
    private void victoryDialogue(){
        String[] names = {"GENERAL VON KROSS", "ZIG", "ZAG", "ZIG"};

        String[] texts = {
            "Vocês destruíram os controlos e a bomba ativou, ninguém sai vivo daqui!",
            "Não podemos desativar a bomba",
            "Mas podemos afastar esta nave de Zanarkin.",
            "Missão cumprida, parceiro. Foi uma honra.",
        };
        
        addObject(new Dialogue(names, texts), 480, 450);
    }
}
