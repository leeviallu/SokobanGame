package org.example.sokoban;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import java.util.Dictionary;
import java.util.Hashtable;

public class Board extends GridPane {
    private final int WALL = 1;
    private final int FLOOR = 2;
    private final int CHARACTER = 3;
    private final int BOX = 4;
    private final int TARGET = 5;
    Dictionary<Pair<Integer, Integer>, Integer> mutableDict = new Hashtable<>();
    Dictionary<Pair<Integer, Integer>, Integer> layoutDict = new Hashtable<>();

    Character hahmo = new Character();

    public Dictionary<Pair<Integer, Integer>, Integer> getDict() {
        return mutableDict;
    }

    public void setDict(Dictionary<Pair<Integer, Integer>, Integer> dict) {
        this.mutableDict = dict;
    }
    String[] board = {
              " #####  ",
              " #   ## ",
              "## * .##",
              "# $$*  #",
              "#  * . #",
              "## @ ###",
              " #####  "
    };

    public Board() {
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

        updateBoard();
    }

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

    public void updateBoard() {
        getChildren().removeAll();
        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (layoutDict.get(new Pair<>(col, row)) == WALL) {
                    add(new Wall(), col, row);
                } else if (layoutDict.get(new Pair<>(col, row)) == FLOOR) {
                    add(new Floor(), col, row);
                } else if (layoutDict.get(new Pair<>(col, row)) == TARGET) {
                    add(new Target(), col, row);
                }
            }
        }
        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (mutableDict.get(new Pair<>(col, row)) == CHARACTER) {
                    add(hahmo, col, row);
                    hahmo.setPosX(col);
                    hahmo.setPosY(row);
                } else if (mutableDict.get(new Pair<>(col, row)) == BOX) {
                    add(new Box(), col, row);
                }
            }
        }

        if (isReady()) {
            add(new Label("Voitit pelin!"), 0,board.length);
        }

    }
}
