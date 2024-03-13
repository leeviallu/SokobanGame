package org.example.sokoban;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Luokan tehtävänä on muodostaa pelissä olevalle kentälle pohja
 */
public class Layout {
    /**
     * Vakio viittaa seinään sanakirjassa
     */
    private final int WALL = 1;
    /**
     * Vakio viittaa lattiaan sanakirjassa
     */
    private final int FLOOR = 2;
    /**
     * Vakio viittaa pelihahmoon sanakirjassa
     */
    private final int CHARACTER = 3;
    /**
     * Vakio viittaa laatikkoon sanakirjassa
     */
    private final int BOX = 4;
    /**
     * Vakio viittaa kohdelaattaan sanakirjassa
     */
    private final int TARGET = 5;
    /**
     * Sanakirja, jossa säilytetään pelissä liikkuvia asioita, kuten pelihahmo ja laatikot
     */
    private Dictionary<Pair<Integer, Integer>, Integer> mutableDict = new Hashtable<>();
    /**
     * Sanakirja, jossa säilytetään pelissä paikallaan säilyviä asioita, kuten seinät ja lattia.
     */
    private final Dictionary<Pair<Integer, Integer>, Integer> layoutDict = new Hashtable<>();
    /**
     * Gridpane sisältää graafisen pelilaudan
     */
    private GridPane gridpane = new GridPane();
    /**
     * Muuttujalla viitataan pelihahmoon
     */
    private Character character = new Character();
    /**
     * Muuttujalla viitataan kenttään, joka halutaan toteuttaa
     */
    private final String[] board;
    /**
     * Metodi, jolla voidaan hakea muuttuva sanakirja
     * @return sanakirja muuttuvista asioista
     */
    public Dictionary<Pair<Integer, Integer>, Integer> getDict() {
        return mutableDict;
    }
    /**
     * Metodi, jolla voidaan hakea pelihahmo
     * @return pelihahmo
     */
    public Character getCharacter() {
        return character;
    }
    /**
     * Metodi, jolla voidaan hakea graafinen pelilauta
     * @return pelilauta
     */
    public GridPane getBoard() {
        return gridpane;
    }

    /**
     * Alustajametodi, jossa sijoitetaan sanakirjoihin kentässä esiintyvät oliot ja kutsutaan metodia,
     * joka luo sanakirjojen pohjalta pelilaudan
     * @param levelList lista pelikentistä
     * @param level kentän numero
     */
    public Layout(Level[] levelList, int level) {
        level --;
        board = levelList[level].getLevel();

        char wallChar = '#';
        char floorChar = ' ';
        char characterChar = '@';
        char boxChar = '$';
        char targetChar = '.';
        char boxOnTargetChar = '*';

        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (arr[col] == wallChar) {
                    layoutDict.put(new Pair<>(col, row), WALL);
                } else if (arr[col] == floorChar || arr[col] == characterChar || arr[col] == boxChar) {
                    layoutDict.put(new Pair<>(col, row), FLOOR);
                } else if (arr[col] == targetChar || arr[col] == boxOnTargetChar) {
                    layoutDict.put(new Pair<>(col, row), TARGET);
                }
            }
        }

        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (arr[col] == wallChar) {
                    mutableDict.put(new Pair<>(col, row), WALL);
                } else if (arr[col] == floorChar) {
                    mutableDict.put(new Pair<>(col, row), FLOOR);
                } else if (arr[col] == characterChar) {
                    mutableDict.put(new Pair<>(col, row), CHARACTER);
                } else if (arr[col] == boxChar || arr[col] == boxOnTargetChar) {
                    mutableDict.put(new Pair<>(col, row), BOX);
                } else if (arr[col] == targetChar) {
                    mutableDict.put(new Pair<>(col, row), TARGET);
                }
            }
        }
        initBoard();
    }

    /**
     * Metodi, joka alustaa pelilaudan sanakirjojen sisällön perusteella.
     */
    public void initBoard() {
        gridpane.getChildren().clear();
        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (layoutDict.get(new Pair<>(col, row)) == WALL) {
                    gridpane.add(new Wall(), col, row);
                } else if (layoutDict.get(new Pair<>(col, row)) == FLOOR) {
                    gridpane.add(new Floor(), col, row);
                } else if (layoutDict.get(new Pair<>(col, row)) == TARGET) {
                    gridpane.add(new Target(), col, row);
                }
            }
        }
        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (mutableDict.get(new Pair<>(col, row)) == CHARACTER) {
                    gridpane.add(character, col, row);
                    character.setPosX(col);
                    character.setPosY(row);
                } else if (mutableDict.get(new Pair<>(col, row)) == BOX) {
                    gridpane.add(new Box(), col, row);
                }
            }
        }
    }
    /**
     * Metodi, jolla voidaan tarkistaa onko pelaaja läpäissyt kentän
     * @return true/false
     */
    public boolean isReady() {
        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (mutableDict.get(new Pair<>(col, row)) == BOX) {
                    if (!(layoutDict.get(new Pair<>(col, row)) == TARGET)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
