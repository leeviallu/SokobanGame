package org.example.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Luokka luo olion kohdelaatalle.
 */
public class Target extends ImageView {
    public Target() {
        setImage(new Image("file:src/main/resources/target.png"));
    }
}
