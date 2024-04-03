package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Luokka luo olion kohdelaatalle.
 */
public class Target extends Rectangle {
    /**
     * Alustaa laatan korkeuden, leveyden ja värin
     */
    public Target() {
        setHeight(50);
        setWidth(50);
        setFill(Color.LIGHTPINK);
    }
}
