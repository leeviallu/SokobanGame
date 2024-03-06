package org.example.sokoban;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Target extends Rectangle {
    private int posX;
    private int posY;
    private Color color = Color.LIGHTPINK;

    public Target() {
        initBox();
    }
    public Target(Color color) {
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
