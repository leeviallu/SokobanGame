package org.example.sokoban;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Game extends Application {
    private int currentLevel;
    private final int DICTCLEAR = 0;
    private final int DICTCHARACTER = 3;
    private final int DICTBOX = 4;
    private Layout layout = new Layout();
    private GridPane gridPane = layout.getBoard();
    private Character character = layout.getCharacter();
    private final VBox vBox = new VBox();
    private final HBox btnBox = new HBox();
    private final Button restartBtn = new Button("Restart");
    private final Button nextBtn = new Button("Next level");
    private final Button prevBtn = new Button("Previous level");


    private boolean running;
    private int charPosX;
    private int charPosY;

    public boolean notWall(int x, int y) {
        Integer value = layout.getDict().get(new Pair<>(x,y));
        int DICTWALL = 1;
        return value == null || value != DICTWALL;
    }
    public boolean isBox(int x, int y) {
        Integer value = layout.getDict().get(new Pair<>(x,y));
        return value != null && value == DICTBOX;
    }
    public void moveLeft() {
        if (isBox(charPosX - 1, charPosY) && notWall(charPosX - 2, charPosY) && !isBox(charPosX - 2, charPosY)) {
            layout.getDict().put(new Pair<>(charPosX - 2, charPosY), DICTBOX);
            character.setPosX(charPosX - 1);
            charPosX = character.getPosX();
        } else if (!isBox(charPosX - 1, charPosY)) {
            character.setPosX(charPosX - 1);
            charPosX = character.getPosX();
        }
    }
    public void moveRight() {
        if (isBox(charPosX + 1, charPosY) && notWall(charPosX + 2, charPosY) && !isBox(charPosX + 2, charPosY)) {
            layout.getDict().put(new Pair<>(charPosX + 2, charPosY), DICTBOX);
            character.setPosX(charPosX + 1);
            charPosX = character.getPosX();
        } else if (!isBox(charPosX + 1, charPosY)) {
            character.setPosX(charPosX + 1);
            charPosX = character.getPosX();
        }
    }
    public void moveUp() {
        if (isBox(charPosX, charPosY - 1) && notWall(charPosX, charPosY - 2) && !isBox(charPosX, charPosY - 2)) {
            layout.getDict().put(new Pair<>(charPosX, charPosY - 2), DICTBOX);
            character.setPosY(charPosY - 1);
            charPosY = character.getPosY();
        } else if (!isBox(charPosX, charPosY - 1)) {
            character.setPosY(charPosY - 1);
            charPosY = character.getPosY();
        }
    }
    public void moveDown() {
        if (isBox(charPosX, charPosY + 1) && notWall(charPosX, charPosY + 2) && !isBox(charPosX, charPosY + 2)) {
            layout.getDict().put(new Pair<>(charPosX, charPosY + 2), DICTBOX);
            character.setPosY(charPosY + 1);
            charPosY = character.getPosY();
        } else if (!isBox(charPosX, charPosY + 1)) {
            character.setPosY(charPosY + 1);
            charPosY = character.getPosY();
        }
    }
    public void restartLevel(int level) {
        running = true;
        vBox.getChildren().clear();
        layout = new Layout(level);
        gridPane = layout.getBoard();
        character = layout.getCharacter();
        vBox.getChildren().addAll(gridPane, btnBox);
    }

    private Scene getScene() {
        Scene scene = new Scene(vBox, 1200, 650);
        scene.setOnKeyPressed(e -> {
            if (running) {
                charPosX = character.getPosX();
                charPosY = character.getPosY();
                layout.getDict().put(new Pair<>(charPosX, charPosY), DICTCLEAR);
                if (e.getCode() == KeyCode.LEFT && notWall(charPosX - 1, charPosY)) {
                    moveLeft();
                } else if (e.getCode() == KeyCode.RIGHT && notWall(charPosX + 1, charPosY)) {
                    moveRight();
                } else if (e.getCode() == KeyCode.UP && notWall(charPosX, charPosY - 1)) {
                    moveUp();
                } else if (e.getCode() == KeyCode.DOWN && notWall(charPosX, charPosY + 1)) {
                    moveDown();
                }
                layout.getDict().put(new Pair<>(charPosX, charPosY), DICTCHARACTER);
                layout.initBoard();
                if (layout.isReady()) {
                    running = false;
                    vBox.getChildren().add(new Label("Voitit pelin!"));
                }
            }
        });
        return scene;
    }

    @Override
    public void start(Stage primaryStage) {
        running = true;
        currentLevel = 0;
        btnBox.getChildren().addAll(prevBtn,restartBtn,nextBtn);
        vBox.getChildren().addAll(gridPane, btnBox);
        restartBtn.setOnAction(e -> {
            restartLevel(currentLevel);
            layout.initBoard();
        });
        restartBtn.setFocusTraversable(false);
        nextBtn.setOnAction(e -> {
            if (layout.levels.length > currentLevel + 1) {
                currentLevel ++;
                restartLevel(currentLevel);
                layout.initBoard();
            }
        });
        nextBtn.setFocusTraversable(false);
        prevBtn.setOnAction(e -> {
            if (currentLevel != 0) {
                currentLevel --;
                restartLevel(currentLevel);
                layout.initBoard();
            }
        });
        prevBtn.setFocusTraversable(false);

        Scene scene = getScene();
        primaryStage.setTitle("Kenttä 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
