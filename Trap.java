import greenfoot.*;

//Armadilha no chão: tira uma vida ao agente que lhe tocar por cima.
 
public class Trap extends Solid
{
    //Armadilha com o tamanho de um quadrado do mapa
    public Trap() {
        super(Settings.TILE_SIZE, Settings.TILE_SIZE);
    }
    //Armadilha com largura e altura à escolha
    public Trap(int width, int height) {
        super(width, height);
    }

    @Override
    public void act() {
        damageAgentsOnTop();
    }
    //Fere os agentes cujos pés tocam no topo da armadilha
    private void damageAgentsOnTop() {
        if (getWorld() == null) return;

        int trapTop = getY() - getImage().getHeight() / 2;

        for (Agent player : getWorld().getObjects(Agent.class)) {
            int horizontalDistance = Math.abs(player.getX() - getX());
            int maxDistance = (player.getImage().getWidth() + getImage().getWidth()) / 2;

            boolean touchingTop = Math.abs(player.getFeetY() - trapTop) <= Settings.TRAP_TOP_TOLERANCE;
            boolean overlappingHorizontally = horizontalDistance < maxDistance;

            if (touchingTop && overlappingHorizontally) {
                player.takeDamage();
            }
        }
    }
}
