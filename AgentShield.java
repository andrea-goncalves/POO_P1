import greenfoot.*;
/**
 * Escudo levantado por um agente no combate com o boss.
 * É um bloco sólido que trava os projéteis.
 */
public class AgentShield extends Solid {
    //Cria o escudo com o tamanho definido nos Settings
    public AgentShield(){
        super(Settings.AGENT_SHIELD_WIDTH, Settings.AGENT_SHIELD_HEIGHT);
    }
}
