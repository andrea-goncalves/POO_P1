import greenfoot.*;
/**
 * Projétil disparado por inimigos. Anda na horizontal, tira uma vida
 * ao primeiro agente que tocar e desaparece ao bater numa parede.
 */
public class Bullet extends Actor {
    private int speed;
    private int direction;
    //Bala normal do soldado (um retângulo vermelho pequeno)
    public Bullet(int direction){
        this.direction = direction;
        this.speed = Settings.BULLET_SPEED;

        GreenfootImage img = new GreenfootImage(Settings.BULLET_WIDTH, Settings.BULLET_HEIGHT);
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
    }
    //Projétil redondo com velocidade e tamanho à escolha (usado pelo boss)
    public Bullet(int direction, int speed, int diameter){
        this.direction = direction;
        this.speed = speed;

        GreenfootImage img = new GreenfootImage(diameter, diameter);
        img.setColor(Color.RED);
        img.fillOval(0, 0, diameter, diameter);
        setImage(img);
    }
    //Move o projétil e trata das colisões com paredes, bordas e agentes
    @Override
    public void act(){
        setLocation(getX() + direction * speed, getY());

        if (isTouching(Solid.class) || isAtEdge()) {
            getWorld().removeObject(this);
            return;
        }

        Agent agent = (Agent) getOneIntersectingObject(Agent.class);

        if (agent != null) {
            agent.takeDamage();
            getWorld().removeObject(this);
        }
    }
}