package org.example.sokoban;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Game extends Application {
    private final int DICTWALL = 1;
    private final int DICTFLOOR = 2;
    private final int DICTBOX = 4;
    private int charPosX;
    private int charPosY;


    Board paneeli = new Board();
    Character hahmo = paneeli.hahmo;
    int lastpos;

    public boolean isWall(int x, int y) {
        Integer value = paneeli.getDict().get(new Pair<>(x,y));
        return value != null && value == DICTWALL;
    }
    public boolean isBox(int x, int y) {
        Integer value = paneeli.getDict().get(new Pair<>(x,y));
        return value != null && value == DICTBOX;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene kehys = new Scene(paneeli, 1200, 650);

        kehys.setOnKeyPressed(e -> {
            charPosX = hahmo.getPosX();
            charPosY = hahmo.getPosY();

            paneeli.getDict().put(new Pair<>(charPosX,charPosY), DICTFLOOR);
            if (e.getCode() == KeyCode.LEFT && !isWall(charPosX-1,charPosY)) {
                if (isBox(charPosX-1,charPosY) && !isWall(charPosX-2,charPosY) && !isBox(charPosX-2,charPosY)) {
                    paneeli.getDict().put(new Pair<>(charPosX-2,charPosY), DICTBOX);
                    hahmo.setPosX(charPosX-1);
                    charPosX = hahmo.getPosX();
                } else if (!isBox(charPosX-1,charPosY)) {
                    hahmo.setPosX(charPosX-1);
                    charPosX = hahmo.getPosX();
                }
            } else if (e.getCode() == KeyCode.RIGHT && !isWall(charPosX+1,charPosY)) {
                if (isBox(charPosX+1,charPosY) && !isWall(charPosX+2,charPosY) && !isBox(charPosX+2,charPosY)) {
                    paneeli.getDict().put(new Pair<>(charPosX+2,charPosY), DICTBOX);
                    hahmo.setPosX(charPosX+1);
                    charPosX = hahmo.getPosX();
                } else if (!isBox(charPosX+1,charPosY)) {
                    hahmo.setPosX(charPosX+1);
                    charPosX = hahmo.getPosX();
                }
            } else if (e.getCode() == KeyCode.UP && !isWall(charPosX,charPosY-1)) {
                if (isBox(charPosX,charPosY-1) && !isWall(charPosX,charPosY-2) && !isBox(charPosX,charPosY-2)) {
                    paneeli.getDict().put(new Pair<>(charPosX,charPosY-2), DICTBOX);
                    hahmo.setPosY(charPosY-1);
                    charPosY = hahmo.getPosY();
                } else if (!isBox(charPosX,charPosY-1)) {
                    hahmo.setPosY(charPosY-1);
                    charPosY = hahmo.getPosY();
                }
            } else if (e.getCode() == KeyCode.DOWN && !isWall(charPosX,charPosY+1)) {
                if (isBox(charPosX,charPosY+1) && !isWall(charPosX,charPosY+2) && !isBox(charPosX,charPosY+2)) {
                    paneeli.getDict().put(new Pair<>(charPosX,charPosY+2), DICTBOX);
                    hahmo.setPosY(charPosY+1);
                    charPosY = hahmo.getPosY();
                } else if (!isBox(charPosX,charPosY+1)) {
                    hahmo.setPosY(charPosY+1);
                    charPosY = hahmo.getPosY();
                }
            }
            paneeli.getDict().put(new Pair<>(charPosX,charPosY), 3);
            paneeli.getChildren().removeAll(hahmo);
            paneeli.updateBoard();

        });

        primaryStage.setTitle("Kenttä 1");
        primaryStage.setScene(kehys);
        primaryStage.show();
    }
}
