package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Luokka luo olion seinälle.
 */
public class Wall extends RectangleComponent {
    /**
     * Alustaa seinän korkeuden, leveyden ja värin
     */
    public Wall() {
        setFill(Color.BLACK);;
    }
}
