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
    public int getLevelNumber(){
        return 4;
    }
    private void introDialogue(){
        String[] names = {"GENERAL VON KROSS", "ZAG", "ZIG"};

        String[] texts = {
            "Ratos de Zanarkin! Chegaram tarde demais!",
            "A tua bomba não vai tocar em Zanarkin!",
            "Rende-te, Von Kross! A fortaleza não vai a lado nenhum!"
        };
        
        addObject(new Dialogue(names, texts), 480, 450);
    }
    private void victoryDialogue(){
        String[] names = {"GENERAL VON KROSS", "ZAG", "ZIG", "ZAG"};

        String[] texts = {
            "Vocês destruíram os controles e a bomba ativou; ninguém sai vivo daqui!",
            "(No painel em chamas) Não dá pra pousar nem desarmar! É o fim, Zig!",
            "(Puxando o manche) Mas dá pra guiar essa nave pro oceano e salvar Zanarkin.",
            "(Segurando o manche junto) Missão cumprida, parceiro. Foi uma honra.",
        };
        
        addObject(new Dialogue(names, texts), 480, 450);
        //Greenfoot.stop();
    }
}
