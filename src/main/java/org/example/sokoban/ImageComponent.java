package org.example.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ImageComponent extends ImageView {
    public ImageComponent(String path) {
        setImage(new Image(path, 50, 50, true, true));
    }
}
