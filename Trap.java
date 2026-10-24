import greenfoot.*;

public class Trap extends Solid
{
    public Trap() {
        super(Level.TILE_SIZE, Level.TILE_SIZE);
    }

    public Trap(int width, int height) {
        super(width, height);
    }
    @Override
    public void act() {
        damageAgentsOnTop();
    }

    private void damageAgentsOnTop()  {
        if (getWorld() == null) return;

        for (Agent player : getWorld().getObjects(Agent.class)) {

            int playerBottom = player.getY() + player.getImage().getHeight() / 2;
            int trapTop = getY() - getImage().getHeight() / 2;
            int horizontalDistance = Math.abs(player.getX() - getX());
            int maxDistance =(player.getImage().getWidth() + getImage().getWidth()) / 2;
            boolean touchingTop = Math.abs(playerBottom - trapTop) <= 5;
            boolean overlappingHorizontally = horizontalDistance < maxDistance;

            if (touchingTop && overlappingHorizontally) {
                player.takeDamage();
            }
        }
    }
}
