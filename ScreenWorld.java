import greenfoot.*;
/**
 * Classe base dos ecrãs de texto (menu, pausa, fim de jogo, vitória e história).
 * Oferece métodos para criar o fundo e desenhar texto.
 */
public abstract class ScreenWorld extends World
{
    //Cria um mundo com o tamanho padrão do jogo
    public ScreenWorld()
    {
        super(Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT, Settings.CELL_SIZE);
    }
    //Cria uma imagem de fundo com a cor padrão dos ecrãs
    public GreenfootImage createBackground()
    {
        GreenfootImage img = new GreenfootImage(getWidth(), getHeight());
        img.setColor(Settings.SCREEN_BACKGROUND);
        img.fill();
        return img;
    }
    //Desenha texto com o canto superior esquerdo na posição (x, y)
    public void drawText(GreenfootImage target, String text, int size, Color color, int x, int y)
    {
        GreenfootImage line = new GreenfootImage(text, size, color, Settings.TRANSPARENT);
        target.drawImage(line, x, y);
    }
    //Desenha texto centrado na horizontal, na linha y
    public void drawCentered(GreenfootImage target, String text, int size, Color color, int y)
    {
        GreenfootImage line = new GreenfootImage(text, size, color, Settings.TRANSPARENT);
        target.drawImage(line, (target.getWidth() - line.getWidth()) / 2, y);
    }
}

