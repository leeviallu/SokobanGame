package org.example.sokoban;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game extends Application {
    private final int rows = 10;
    private final int columns = 10;
    Character hahmo = new Character();
    Box laatikko = new Box();
    GridPane paneeli = new Board(rows, columns);

    @Override
    public void start(Stage primaryStage) {
        paneeli.add(hahmo, hahmo.getPosX(), hahmo.getPosY());
        paneeli.add(laatikko, 5,5);


        Scene kehys = new Scene(paneeli, 500, 500);
        kehys.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT && 0 < hahmo.getPosX()) {
                hahmo.setPosX(hahmo.getPosX()-1);
            } else if (e.getCode() == KeyCode.RIGHT && hahmo.getPosX() < (columns - 1)) {
                hahmo.setPosX(hahmo.getPosX()+1);
            } else if (e.getCode() == KeyCode.UP && 0 < hahmo.getPosY()) {
                hahmo.setPosY(hahmo.getPosY()-1);
            } else if (e.getCode() == KeyCode.DOWN && hahmo.getPosY() < (rows - 1)) {
                hahmo.setPosY(hahmo.getPosY()+1);
            }
            paneeli.getChildren().remove(hahmo);
            paneeli.add(hahmo,hahmo.getPosX(),hahmo.getPosY());
        });


        primaryStage.setTitle("Kenttä 1");
        primaryStage.setScene(kehys);
        primaryStage.show();
    }
}
