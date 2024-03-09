package org.example.sokoban;

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
            "              XXXXXXXX",
            "              X  ....X",
            "   XXXXXXXXXXXX  ....X",
            "   X    X  * *   ....X",
            "   X ***X*  * X  ....X",
            "   X  *     * X  ....X",
            "   X ** X* * *XXXXXXXX",
            "XXXX  * X     X       ",
            "X   X XXXXXXXXX       ",
            "X    *  XX            ",
            "X **X** @X            ",
            "X   X   XX            ",
            "XXXXXXXXX             "
    };

    public Board() {
        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (arr[col] == 'X') {
                    layoutDict.put(new Pair<>(col, row), WALL);
                } else if (arr[col] == ' ' || arr[col] == '@' || arr[col] == '*') {
                    layoutDict.put(new Pair<>(col, row), FLOOR);
                } else if (arr[col] == '.') {
                    layoutDict.put(new Pair<>(col, row), TARGET);
                }
            }
        }

        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (arr[col] == 'X') {
                    mutableDict.put(new Pair<>(col, row), WALL);
                } else if (arr[col] == ' ') {
                    mutableDict.put(new Pair<>(col, row), FLOOR);
                } else if (arr[col] == '@') {
                    mutableDict.put(new Pair<>(col, row), CHARACTER);
                } else if (arr[col] == '*') {
                    mutableDict.put(new Pair<>(col, row), BOX);
                } else if (arr[col] == '.') {
                    mutableDict.put(new Pair<>(col, row), TARGET);
                }
            }
        }

        updateBoard();
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
    }
}
