package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Luokka luo olion lattialaatalle.
 */
public class Floor extends Rectangle {
    /**
     * Alustaa lattialaatan korkeuden, leveyden ja värin
     */
    public Floor() {
        setHeight(50);
        setWidth(50);
        setFill(Color.WHITE);
    }
}
