package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Luokka luo olion kohdelaatalle.
 */
public class Target extends RectangleComponent {
    /**
     * Alustaa laatan korkeuden, leveyden ja värin
     */
    public Target() {
        setFill(Color.LIGHTPINK);
    }
}
