package org.example.sokoban;

/**
 * Luokka luo olion pelihahmolle.
 */
public class Character extends ImageComponent {
    /**
     * Hahmon sijainti x-akselilla
     */
    private int posX;
    /**
     * Hahmon sijainti y-akselilla
     */
    private int posY;
    /**
     * Alustaa olion tiedostopolun avulla
     */
    public Character() {
        super("file:src/main/resources/character.png");
    }
    /**
     * Asettaa hahmolle uuden sijainnin x-akselilla
     * @param posX sijainti x-akselilla
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }
    /**
     * Asettaa hahmolle uuden sijainnin y-akselilla
     * @param posY sijainti y-akselilla
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
    /**
     * Palauttaa hahmon sijainnin x-akselilla
     * @return sijainti x-akselilla
     */
    public int getPosX() {
        return posX;
    }
    /**
     * Palauttaa hahmon sijainnin y-akselilla
     * @return sijainti y-akselilla
     */
    public int getPosY() {
        return posY;
    }
}
