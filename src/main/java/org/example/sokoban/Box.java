package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Box extends Rectangle {
    private Color color = Color.BURLYWOOD;

    public Box() {
        initBox();
    }
    public Box(Color color) {
        this.color = color;
        initBox();
    }
    public void initBox() {
        setHeight(50);
        setWidth(50);
        setFill(color);
    }
}
