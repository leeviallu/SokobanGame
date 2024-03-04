package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Floor extends Rectangle {
    private Color color = Color.WHITE;

    public Floor() {
        initFloor();
    }
    public Floor(Color color) {
        this.color = color;
        initFloor();
    }
    public void initFloor() {
        setHeight(50);
        setWidth(50);
        setFill(color);
    }
}
