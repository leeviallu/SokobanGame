package org.example.sokoban;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Board extends Application {
    private int positionY = 1;
    private int positionX = 2;
    private final int rows = 10;
    private final int colums = 10;
    Character hahmo = new Character();
    Box laatikko = new Box();
    GridPane paneeli = initBoard();

    public GridPane initBoard() {
        GridPane paneeli = new GridPane();

        for (int i = 0; i < rows; i ++) {
            if (i % 2 == 0) {
                for (int j = 1; j < colums; j += 2) {
                    paneeli.add(new Floor(Color.WHITE), j - 1, i);
                    paneeli.add(new Floor(Color.LIGHTGRAY), j, i);
                }
            } else {
                for (int j = 1; j < 10; j += 2) {
                    paneeli.add(new Floor(Color.WHITE), j, i);
                    paneeli.add(new Floor(Color.LIGHTGRAY), j-1, i);
                }
            }
        }
        return paneeli;
    }
    public boolean tyonto(Rectangle laatikko, int x, int y) {
        if (0 <= x && x < colums) {
            paneeli.getChildren().remove(laatikko);
            paneeli.add(laatikko, x, y);
            return true;
        }
        return false;
    }



    @Override
    public void start(Stage primaryStage) {
        paneeli.add(hahmo, positionX, positionY);
        paneeli.add(laatikko, 5,5);


        Scene kehys = new Scene(paneeli, 500, 500);
        kehys.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT && 0 < positionX) {
                positionX--;
                paneeli.getChildren().remove(hahmo);
                paneeli.add(hahmo,positionX,positionY);
            } else if (e.getCode() == KeyCode.RIGHT && positionX < (colums - 1)) {
                positionX++;
                paneeli.getChildren().remove(hahmo);
                paneeli.add(hahmo,positionX,positionY);
            } else if (e.getCode() == KeyCode.UP && 0 < positionY) {
                positionY--;
                paneeli.getChildren().remove(hahmo);
                paneeli.add(hahmo,positionX,positionY);
            } else if (e.getCode() == KeyCode.DOWN && positionY < (rows - 1)) {
                positionY++;
                paneeli.getChildren().remove(hahmo);
                paneeli.add(hahmo,positionX,positionY);
            }
        });


        primaryStage.setTitle("Kenttä 1");
        primaryStage.setScene(kehys);
        primaryStage.show();
    }
}
