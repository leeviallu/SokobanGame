package org.example.sokoban;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Game extends Application {
    Board paneeli = new Board();

    @Override
    public void start(Stage primaryStage) {
        Scene kehys = new Scene(paneeli, 500, 500);
        kehys.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT && 0 < paneeli.hahmo.getPosX()) {
                paneeli.hahmo.setPosX(paneeli.hahmo.getPosX()-1);
            } else if (e.getCode() == KeyCode.RIGHT && paneeli.hahmo.getPosX() < (paneeli.getColumnCount() - 1)) {
                paneeli.hahmo.setPosX(paneeli.hahmo.getPosX()+1);
            } else if (e.getCode() == KeyCode.UP && 0 < paneeli.hahmo.getPosY()) {
                paneeli.hahmo.setPosY(paneeli.hahmo.getPosY()-1);
            } else if (e.getCode() == KeyCode.DOWN && paneeli.hahmo.getPosY() < (paneeli.getRowCount() - 1)) {
                paneeli.hahmo.setPosY(paneeli.hahmo.getPosY()+1);
            }
            paneeli.getChildren().removeAll(paneeli.hahmo);
            paneeli.add(paneeli.hahmo,paneeli.hahmo.getPosX(),paneeli.hahmo.getPosY());
        });


        primaryStage.setTitle("Kenttä 1");
        primaryStage.setScene(kehys);
        primaryStage.show();
    }
}
