import greenfoot.*;

public class BossLevel extends Level {

    private boolean bossDead;

    public BossLevel(){
        super(Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT);
        bossDead = false;
        introDialogue();

        loadCollisions(Settings.BOSS_CSV);
        setBackground(Settings.BOSS_BACKGROUND);

        addAgents(Settings.BOSS_ZIG_X, Settings.BOSS_ZAG_X, Settings.BOSS_AGENT_Y);

        Boss boss = new Boss();
        addObject(boss, Settings.BOSS_START_X, Settings.BOSS_START_Y);
        addObject(new BossHealthBar(boss), Settings.BOSS_BAR_X, Settings.BOSS_BAR_Y);
    }
    // Quando o boss morre e o diálogo final acaba, mostra o ecrã de vitória. 
    @Override
    public void act(){
        super.act();
        if (bossDead && !isDialogueActive()) {
            Greenfoot.setWorld(new WinWorld(getScore()));
        }
    }
    //Chamado pelo boss quando a sua vida chega a zero.
    public void bossDefeated(){
        bossDead = true;
        addLevelBonus();
        SaveGame.clear();
        victoryDialogue();
    }

    @Override
    public Level createNew(){
        return new BossLevel();
    }

    @Override
    public String getPowerName(){
        return Settings.POWER_SHIELD;
    }

    @Override
    public Level nextLevel(){
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
        return Settings.BOSS_LEVEL;
    }
    //Diálogo que aparece no início do combate.
    private void introDialogue(){
        String[] names = {"GENERAL VON KROSS", "ZAG", "ZIG"};

        String[] texts = {
                "Ratos de Zanarkin! Chegaram demasiado tarde!",
                "Essa bomba não vai destruir em Zanarkin!",
                "Rende-te, Von Kross! O teu plano fracassou!"
            };

        addObject(new Dialogue(names, texts), Settings.DIALOGUE_X, Settings.DIALOGUE_Y);
    }
    //Diálogo que aparece depois de o chefe ser derrotado.
    private void victoryDialogue(){
        String[] names = {"GENERAL VON KROSS", "ZIG", "ZAG", "ZIG"};

        String[] texts = {
                "Vocês destruíram os controlos e a bomba ativou, ninguém sai vivo daqui!",
                "Não podemos desativar a bomba",
                "Mas podemos afastar esta nave de Zanarkin.",
                "Missão cumprida, parceiro. Foi uma honra."
            };

        addObject(new Dialogue(names, texts), Settings.DIALOGUE_X, Settings.DIALOGUE_Y);
    }
}