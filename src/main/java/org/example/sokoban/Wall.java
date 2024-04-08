package org.example.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Luokka luo olion seinälle.
 */
public class Wall extends ImageComponent {
    public Wall() {
        super("file:src/main/resources/wall.png");
    }
}
