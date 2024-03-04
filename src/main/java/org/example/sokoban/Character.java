package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Character extends Circle {
    int radius = 25;
    Color color = Color.YELLOW;

    public Character() {
        initCharacter();
    }
    public Character(Color color) {
        this.color = color;
        initCharacter();
    }
    public void initCharacter() {
        setRadius(radius);
        setFill(color);
    }
}
