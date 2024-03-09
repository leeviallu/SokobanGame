package org.example.sokoban;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Game extends Application {
    private final int WALL = 1;
    private final int FLOOR = 2;
    private final int CHARACTER = 3;
    private final int BOX = 4;
    private final int TARGET = 5;
    private int floorType = 2;

    Board paneeli = new Board();
    Character hahmo = paneeli.hahmo;
    int lastpos;

    public boolean isWall(int x, int y) {
        Integer value = paneeli.getDict().get(new Pair<>(x,y));
        return value != null && value == WALL;
    }
    public boolean isBox(int x, int y) {
        Integer value = paneeli.getDict().get(new Pair<>(x,y));
        return value != null && value == BOX;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene kehys = new Scene(paneeli, 1200, 650);

        kehys.setOnKeyPressed(e -> {
            paneeli.getDict().put(new Pair<>(hahmo.getPosX(),hahmo.getPosY()), floorType);
            if (e.getCode() == KeyCode.LEFT && !isWall(hahmo.getPosX()-1,hahmo.getPosY())) {
                if (isBox(hahmo.getPosX()-1,hahmo.getPosY()) && !isWall(hahmo.getPosX()-2,hahmo.getPosY()) && !isBox(hahmo.getPosX()-2,hahmo.getPosY())) {
                    paneeli.getDict().put(new Pair<>(hahmo.getPosX()-2,hahmo.getPosY()), 4);
                    hahmo.setPosX(hahmo.getPosX()-1);
                } else if (!isBox(hahmo.getPosX()-1,hahmo.getPosY())) {
                    floorType = paneeli.getDict().get(new Pair<>(hahmo.getPosX()-1,hahmo.getPosY()));
                    hahmo.setPosX(hahmo.getPosX()-1);
                }
            } else if (e.getCode() == KeyCode.RIGHT && !isWall(hahmo.getPosX()+1,hahmo.getPosY())) {
                if (isBox(hahmo.getPosX()+1,hahmo.getPosY()) && !isWall(hahmo.getPosX()+2,hahmo.getPosY()) && !isBox(hahmo.getPosX()+2,hahmo.getPosY())) {
                    paneeli.getDict().put(new Pair<>(hahmo.getPosX()+2,hahmo.getPosY()), 4);
                    hahmo.setPosX(hahmo.getPosX()+1);
                } else if (!isBox(hahmo.getPosX()+1,hahmo.getPosY())) {
                    floorType = paneeli.getDict().get(new Pair<>(hahmo.getPosX()+1,hahmo.getPosY()));
                    hahmo.setPosX(hahmo.getPosX()+1);
                }
            } else if (e.getCode() == KeyCode.UP && !isWall(hahmo.getPosX(),hahmo.getPosY()-1)) {
                if (isBox(hahmo.getPosX(),hahmo.getPosY()-1) && !isWall(hahmo.getPosX(),hahmo.getPosY()-2) && !isBox(hahmo.getPosX(),hahmo.getPosY()-2)) {
                    paneeli.getDict().put(new Pair<>(hahmo.getPosX(),hahmo.getPosY()-2), 4);
                    hahmo.setPosY(hahmo.getPosY()-1);
                } else if (!isBox(hahmo.getPosX(),hahmo.getPosY()-1)) {
                    floorType = paneeli.getDict().get(new Pair<>(hahmo.getPosX(),hahmo.getPosY()-1));

                    hahmo.setPosY(hahmo.getPosY()-1);
                }
            } else if (e.getCode() == KeyCode.DOWN && !isWall(hahmo.getPosX(),hahmo.getPosY()+1)) {
                if (isBox(hahmo.getPosX(),hahmo.getPosY()+1) && !isWall(hahmo.getPosX(),hahmo.getPosY()+2) && !isBox(hahmo.getPosX()+2,hahmo.getPosY())) {
                    paneeli.getDict().put(new Pair<>(hahmo.getPosX(),hahmo.getPosY()+2), 4);
                    hahmo.setPosY(hahmo.getPosY()+1);
                } else if (!isBox(hahmo.getPosX(),hahmo.getPosY()+1)) {
                    floorType = paneeli.getDict().get(new Pair<>(hahmo.getPosX(),hahmo.getPosY()+1));

                    hahmo.setPosY(hahmo.getPosY()+1);
                }
            }
            paneeli.getDict().put(new Pair<>(hahmo.getPosX(),hahmo.getPosY()), 3);
            paneeli.getChildren().removeAll(hahmo);
            paneeli.updateBoard();

        });

        primaryStage.setTitle("Kenttä 1");
        primaryStage.setScene(kehys);
        primaryStage.show();
    }
}
