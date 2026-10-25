import greenfoot.*;
/**
 * Funções auxiliares para carregar sequências de imagens (animações)
 * e para criar as versões espelhadas das imagens.
 */
public class Frames
{
    //Carrega prefixo1.png, prefixo2.png, ... até ao número de frames indicado
    public static GreenfootImage[] load(String prefix, int count)
    {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++) {
            frames[i] = new GreenfootImage(prefix + (i + 1) + Settings.IMAGE_EXTENSION);
        }
        return frames;
    }
    //Devolve uma cópia espelhada de cada imagem (para virar a personagem para o outro lado)
    public static GreenfootImage[] mirror(GreenfootImage[] frames)
    {
        GreenfootImage[] flipped = new GreenfootImage[frames.length];
        for (int i = 0; i < frames.length; i++) {
            flipped[i] = new GreenfootImage(frames[i]);
            flipped[i].mirrorHorizontally();
        }
        return flipped;
    }
}