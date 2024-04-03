package org.example.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Luokka luo olion seinälle.
 */
public class Wall extends ImageView {
    public Wall() {
        setImage(new Image("file:src/main/resources/wall.png"));
    }
}
