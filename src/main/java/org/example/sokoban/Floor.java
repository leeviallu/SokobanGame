package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Luokka luo olion lattialaatalle.
 */
public class Floor extends RectangleComponent {
    /**
     * Alustaa lattialaatan korkeuden, leveyden ja värin
     */
    public Floor() {
        setFill(Color.WHITE);
    }
}
