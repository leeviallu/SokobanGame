package org.example.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstrakti luokka, jonka avulla tehdään tasoihin soveltuvia komponentteja
 */
public abstract class ImageComponent extends ImageView {
    /**
     * Alustaa luokan toiminnan tiedostopolun perusteella
     * @param path tiedostopolku
     */
    public ImageComponent(String path) {
        setImage(new Image(path, 50, 50, true, true));
    }
}
