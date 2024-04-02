package org.example.sokoban;

import javafx.scene.shape.Rectangle;

public abstract class Component extends Rectangle {
    public Component() {
        setHeight(50);
        setWidth(50);
    }
}
