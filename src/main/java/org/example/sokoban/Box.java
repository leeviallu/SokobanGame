package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Luokka luo olion laatikolle.
 */
public class Box extends Rectangle {
    /**
     * Laatikon väri
     */
    private final Color color = Color.BURLYWOOD;
    /**
     * Laatikon korkeus
     */
    private final int height = 50;
    /**
     * Laatikon leveys
     */
    private final int width = 50;

    /**
     * Alustaja luokalle
     */
    public Box() {
        setHeight(height);
        setWidth(width);
        setFill(color);
    }
}
