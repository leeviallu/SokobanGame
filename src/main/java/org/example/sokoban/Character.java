package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Character extends Circle {
    private int posX = 2;
    private int posY = 1;
    Color color = Color.YELLOW;

    public Character() {
        initCharacter();
    }
    public Character(Color color) {
        this.color = color;
        initCharacter();
    }
    public void initCharacter() {
        setRadius(25);
        setFill(color);
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
}
