import greenfoot.*;
/**
 * Camada preta semitransparente que cobre o ecrã
 * quando o poder lightsOff está ativo.
 */
public class DarknessOverlay extends Actor
{
    //Cria uma imagem do tamanho do mundo
    public DarknessOverlay(int width, int height)
    {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(Settings.DARKNESS_COLOR);
        img.fill();
        setImage(img);
    }
}
