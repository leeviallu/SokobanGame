package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Rectangle {
    private Color color = Color.BLACK;

    public Wall() {
        initWall();
    }
    public Wall(Color color) {
        this.color = color;
        initWall();
    }
    public void initWall() {
        setHeight(50);
        setWidth(50);
        setFill(color);
    }
}
