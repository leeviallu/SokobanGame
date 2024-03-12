package org.example.sokoban;

import javafx.application.Application;
import javafx.geometry.Insets;
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
    private final int DICTCLEAR = 0;
    private final int DICTCHARACTER = 3;
    private final int DICTBOX = 4;
    private Layout layout;
    private GridPane gridPane;
    private Character character;
    private long startTime;
    private int currentLevel = 1;
    private Level[] levelList;
    private Levels levels = new Levels();
    private final VBox vBox = new VBox();
    private final HBox btnBox = new HBox();
    private final HBox infoBox = new HBox();
    private final Button restartBtn = new Button("Restart");
    private final Button nextBtn = new Button("Next level");
    private final Button prevBtn = new Button("Previous level");
    private final Label highscoreLbl = new Label("");
    private final Label levelNumberLbl = new Label("");
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
        layout = new Layout(levelList, level);
        gridPane = layout.getBoard();
        character = layout.getCharacter();
        levelNumberLbl.setText("Taso: " + levels.getLevels()[currentLevel - 1].getLevelNumber());
        if (levels.getLevels()[currentLevel - 1].getRecordTime() == Double.POSITIVE_INFINITY) {
            highscoreLbl.setText("Kenttää ei ole vielä läpäisty");
        } else {
            highscoreLbl.setText("Huippuaika: " + levels.getLevels()[currentLevel -1].getRecordTime());
        }
        vBox.getChildren().addAll(infoBox, gridPane, btnBox);
        startTime = System.nanoTime();
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
                    double levelTime = (double) (System.nanoTime() - startTime)/1_000_000_000;
                    for (Level i : levelList) {
                        if (i.getLevelNumber() == currentLevel) {
                            if (levelTime < i.getRecordTime()) {
                                i.setRecordTime(levelTime);
                                highscoreLbl.setText("Huippuaika: " + i.getRecordTime());
                                levels.writeFile();
                            }
                        }
                    }
                    levels = new Levels();
                    running = false;
                    HBox completedPane = new HBox(new Label("Läpäisit tason!"));
                    completedPane.setPadding(new Insets(10));
                    vBox.getChildren().add(completedPane);
                }
            }
        });
        return scene;
    }

    @Override
    public void start(Stage primaryStage) {
        levels.writeFile();
        levels.readFile();
        levelList = levels.getLevels();

        layout = new Layout(levelList, 1);
        gridPane = layout.getBoard();
        character = layout.getCharacter();

        running = true;
        levelNumberLbl.setText("Taso: " + levels.getLevels()[0].getLevelNumber());
        if (levels.getLevels()[0].getRecordTime() == Double.POSITIVE_INFINITY) {
            highscoreLbl.setText("Kenttää ei ole vielä läpäisty");
        } else {
            highscoreLbl.setText("Huippuaika: " + levels.getLevels()[0].getRecordTime());
        }


        infoBox.setPadding(new Insets(10));
        infoBox.setSpacing(40);
        infoBox.getChildren().addAll(levelNumberLbl, highscoreLbl);
        btnBox.getChildren().addAll(prevBtn,restartBtn,nextBtn);
        vBox.getChildren().addAll(infoBox, gridPane, btnBox);
        restartBtn.setOnAction(e -> {
            restartLevel(currentLevel);
            layout.initBoard();
        });
        restartBtn.setFocusTraversable(false);
        nextBtn.setOnAction(e -> {
            if (levelList.length > currentLevel) {
                currentLevel ++;
                restartLevel(currentLevel);
                layout.initBoard();
            }
        });
        nextBtn.setFocusTraversable(false);
        prevBtn.setOnAction(e -> {
            if (currentLevel != 1) {
                currentLevel --;
                restartLevel(currentLevel);
                layout.initBoard();
            }
        });
        prevBtn.setFocusTraversable(false);
        startTime = System.nanoTime();
        Scene scene = getScene();
        primaryStage.setTitle("Sokoban");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
