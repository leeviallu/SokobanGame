package org.example.sokoban;

/**
 * Luokka luo olion seinälle.
 */
public class Wall extends ImageComponent {
    /**
     * Alustaa olion tiedostopolun avulla
     */
    public Wall() {
        super("file:src/main/resources/wall.png");
    }
}
