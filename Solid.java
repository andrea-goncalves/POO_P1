import greenfoot.*;
/**
 * Bloco invisível e sólido: serve de chão, parede ou teto.
 * É a classe base de todos os objetos que bloqueiam a passagem.
 */
public class Solid extends Actor
{
    //Bloco com o tamanho de um quadrado do mapa
    public Solid() {
        this(Settings.TILE_SIZE, Settings.TILE_SIZE);
    }
    //Bloco com largura e altura à escolha (imagem transparente)
    public Solid(int width, int height) {
        setImage (new GreenfootImage(width, height));
    }
}