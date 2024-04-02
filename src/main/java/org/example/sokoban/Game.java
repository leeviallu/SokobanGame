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

/** Luokka toteuttaa Sokoban tyylisen pulmapelin.
 * @author Leevi Leppänen
 * @version 1.0 2024/03/13
 */
public class Game extends Application {
    /**
     * Vakio kuvaa sanakirjassa kohtaa, josta hahmo on liikkunut pois.
     */
    private final int DICTCLEAR = 0;
    /**
     * Vakio kuvaa sanakirjassa kohtaa, johon hahmo on liikkunut
     */
    private final int DICTCHARACTER = 3;
    /**
     * Vakio kuvaa sanakirjassa kohtaa, johon laatikko on liikutettu.
     */
    private final int DICTBOX = 4;
    /**
     * Muuttuja luokalle, jossa tehdään kentälle pohja sanakirjan perusteella.
     */
    private Layout layout;
    /**
     * Muuttuja, johon lisätään pelin kenttä
     */
    private GridPane gridPane;
    /**
     * Muuttuja, jolla viitataan pelihahmoon
     */
    private Character character;
    /**
     * Muuttuja, jota käytetään kentän läpäisyajan mittaamiseen
     */
    private long startTime;
    /**
     * Muuttuja, joka kuvaa kenttää, jossa ollaan menossa
     */
    private int currentLevel = 1;
    /**
     * Pelin kentät sisältävä lista, joka muodostetaan tiedostoon tallennetuista kentistä
     */
    private final InitLevels levels = new InitLevels();
    /**
     * VBox, jonka sisällä luokan muu graafinen sisältö on
     */
    private final VBox vBox = new VBox();
    /**
     * HBox, jonka sisällä on pelissä käytettävät napit
     */
    private final HBox btnBox = new HBox();
    /**
     * HBox, jonka sisällä on kentästä kerrottavat tiedot
     */
    private final HBox infoBox = new HBox();
    /**
     * Button, jolla kenttä voidaan aloittaa alusta
     */
    private final Button restartBtn = new Button("Restart");
    /**
     * Button, jolla päästään seuraavaan kenttään
     */
    private final Button nextBtn = new Button("Next level");
    /**
     * Button, jolla päästään aikaisempaan kenttään
     */
    private final Button prevBtn = new Button("Previous level");
    /**
     * Label, jossa kerrotaan kentän numero
     */
    private final Label levelNumberLbl = new Label("");
    /**
     * Label parhaalle tulokselle
     */
    private final Label highScoreLbl = new Label("");
    /**
     * Muuttuja, joka kuvastaa pelin tilaa
     */
    private boolean running;
    /**
     * Muuttuja, joka kuvaa x-akselilla pelihahmon sijaintia
     */
    private int charPosX;
    /**
     * Muuttuja, joka kuvaa y-akselilla pelihahmon sijaintia
     */
    private int charPosY;

    /**
     * Metodi selvittää, eihän tietyssä kohdassa ole seinää
     * @param x sijainti x-akselilla
     * @param y sijainti y-akselilla
     * @return true/false
     */
    public boolean notWall(int x, int y) {
        Integer value = layout.getDict().get(new Pair<>(x,y));
        int DICTWALL = 1;
        return value == null || value != DICTWALL;
    }
    /**
     * Metodi selvittää, onko tietyssä kohdassa laatikko
     * @param x sijainti x-akselilla
     * @param y sijainti y-akselilla
     * @return true/false
     */
    public boolean isBox(int x, int y) {
        Integer value = layout.getDict().get(new Pair<>(x,y));
        return value != null && value == DICTBOX;
    }
    /**
     * Metodi mahdollistaa kentässä liikkumisen vasemmalle
     */
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
    /**
     * Metodi mahdollistaa kentässä liikkumisen oikealle
     */
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
    /**
     * Metodi mahdollistaa kentässä liikkumisen ylös
     */
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
    /**
     * Metodi mahdollistaa kentässä liikkumisen alas
     */
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
    /**
     * Metodi renderöi kentän uudelleen
     * @param level kenttä, joka halutaan renderöidä
     */
    public void restartLevel(int level) {
        running = true;
        vBox.getChildren().clear();
        layout = new Layout(InitLevels.levels, level);
        gridPane = layout.getBoard();
        character = layout.getCharacter();
        levelNumberLbl.setText("Level: " + InitLevels.levels[currentLevel - 1].getLevelNumber());
        if (InitLevels.levels[currentLevel - 1].getRecordTime() == Double.POSITIVE_INFINITY) {
            highScoreLbl.setText("The level hasn't been passed yet");
        } else {
            highScoreLbl.setText("High Score: " + Math.round(InitLevels.levels[currentLevel -1].getRecordTime() * 100.0) / 100.0 + " seconds");
        }
        vBox.getChildren().addAll(infoBox, gridPane, btnBox);
        startTime = System.nanoTime();
    }
    /**
     * Metodi muokkaa tason ennätysaikaa, jos sellainen on tehty ja ilmoittaa käyttäjälle, että taso on läpi.
     */
    public void handleLevelCompleted() {
        double levelTime = (double) (System.nanoTime() - startTime)/1_000_000_000;
        for (Level i : InitLevels.levels) {
            if (i.getLevelNumber() == currentLevel) {
                if (levelTime < i.getRecordTime()) {
                    i.setRecordTime(levelTime);

                    highScoreLbl.setText("High Score: " + Math.round(i.getRecordTime() * 100) / 100.0 + " seconds");
                    InitLevels.writeFile(InitLevels.levels);
                }
            }
        }
        running = false;
        HBox completedPane = new HBox(new Label("Level completed!"));
        completedPane.setPadding(new Insets(10));
        vBox.getChildren().add(completedPane);
    }

    /**
     * Metodi palauttaa luokan kehyksen. Metodissa mahdollistetaan liikkuminen näppäimistön nappeja painamalla
     * ja tarkistetaan, onko kenttä mennyt läpi.
     */
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
                    handleLevelCompleted();
                }
            }
        });
        return scene;
    }
    /**
     * Metodissa alustetaan sovelluksen näkymä,
     * johon kuuluu tason tiedot, taso sekä napit tason vaihtamiseen ja uudelleenkäynnistykseen.
     */
    public void initView() {
        gridPane = layout.getBoard();
        character = layout.getCharacter();

        levelNumberLbl.setText("Level: " + InitLevels.levels[0].getLevelNumber());
        if (InitLevels.levels[0].getRecordTime() == Double.POSITIVE_INFINITY) {
            highScoreLbl.setText("The level hasn't been passed yet");
        } else {
            highScoreLbl.setText("High Score: " + Math.round(InitLevels.levels[0].getRecordTime() * 100.0) / 100.0 + " seconds");
        }

        infoBox.setPadding(new Insets(10));
        infoBox.setSpacing(40);
        infoBox.getChildren().addAll(levelNumberLbl, highScoreLbl);
        btnBox.getChildren().addAll(prevBtn,restartBtn,nextBtn);
        vBox.getChildren().addAll(infoBox, gridPane, btnBox);
        restartBtn.setOnAction(e -> {
            restartLevel(currentLevel);
            layout.initBoard();
        });
        restartBtn.setFocusTraversable(false);
        nextBtn.setOnAction(e -> {
            if (InitLevels.levels.length > currentLevel) {
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
    }
    /**
     * Metodissa haetaan käytettävät tasot, alustetaan näkymä ja käynnistetään suoritus.
     */
    @Override
    public void start(Stage primaryStage) {
        InitLevels.writeFile(InitLevels.levels);
        layout = new Layout(InitLevels.readFile(), 1);

        initView();
        running = true;
        startTime = System.nanoTime();
        Scene scene = getScene();
        primaryStage.setTitle("Sokoban");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
