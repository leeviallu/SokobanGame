package org.example.sokoban;

/**
 * Luokka luo olion kohdelaatalle.
 */
public class Target extends ImageComponent {
    /**
     * Alustaa olion tiedostopolun avulla
     */
    public Target() {
        super("file:src/main/resources/target.png");
    }
}
