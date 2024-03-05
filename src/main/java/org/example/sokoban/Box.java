package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Box extends Rectangle {
    private int posX = 5;
    private int posY = 4;
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
