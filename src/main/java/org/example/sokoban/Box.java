package org.example.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Luokka luo olion laatikolle.
 */
public class Box extends ImageView {
    public Box() {
        setImage(new Image("file:src/main/resources/box.png"));
    }
}
