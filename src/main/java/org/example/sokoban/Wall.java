package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Luokka luo olion seinälle.
 */
public class Wall extends Rectangle {
    /**
     * Alustaa seinän korkeuden, leveyden ja värin
     */
    public Wall() {
        setHeight(50);
        setWidth(50);
        setFill(Color.BLACK);;
    }
}
