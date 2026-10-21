import greenfoot.*;

public class BossLevel extends Level {
    public BossLevel(){
        super(960, 540);
        
        dialogue();
        loadCollisions("Colisao_boss.csv");
        setBackground("faseBoss.png");

        addObject(new Agent(5, 6, 16, "a", "d", "w", "s", "zig"), 100, 223);
        addObject(new Agent(5, 6, 16, "left", "right", "up", "down", "zag"), 160, 223);

        Boss boss = new Boss();
        addObject(boss, 894, 223);//895 223
        addObject(new BossHealthBar(boss), 480, 510);
    }

    public void bossDefeated(){
        dialogue2();
        addScore(10);
        //showText("BOSS DERROTADO! Score final: " + getScore(), 480, 250);
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
    public void dialogue(){
        String[] names = {"GENERAL VON KROSS", "ZAG", "ZIG"};

        String[] texts = {
            "Ratos de Zanarkin! Chegaram tarde demais!",
            "A tua bomba não vai tocar em Zanarkin!",
            "Rende-te, Von Kross! A fortaleza não vai a lado nenhum!"
        };
        
        addObject(new Dialogue(names, texts), 480, 450);
    }
    public void dialogue2(){
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
