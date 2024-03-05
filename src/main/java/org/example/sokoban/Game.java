package org.example.sokoban;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game extends Application {
    private int positionY = 1;
    private int positionX = 2;
    private final int rows = 10;
    private final int columns = 10;
    Character hahmo = new Character();
    Box laatikko = new Box();
    GridPane paneeli = new Board(rows, columns);


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
            } else if (e.getCode() == KeyCode.RIGHT && positionX < (columns - 1)) {
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
