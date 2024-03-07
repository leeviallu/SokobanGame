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
    Dictionary<Pair<Integer, Integer>, Integer> dict = new Hashtable<>();
    Character hahmo = new Character();

    public Board() {
        initBoard();
    }

    public Dictionary<Pair<Integer, Integer>, Integer> getDict() {
        return dict;
    }

    public void setDict(Dictionary<Pair<Integer, Integer>, Integer> dict) {
        this.dict = dict;
    }

    public void initBoard() {
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
        for (int row = 0; row < board.length; row++) {
            char[] arr = board[row].toCharArray();
            for (int col = 0; col < arr.length; col++) {
                if (arr[col] == 'X') {
                    dict.put(new Pair<>(col, row), WALL);
                    add(new Wall(), col, row);
                } else if (arr[col] == ' ') {
                    dict.put(new Pair<>(col, row), FLOOR);
                    add(new Floor(), col, row);
                } else if (arr[col] == '@') {
                    dict.put(new Pair<>(col, row), CHARACTER);
                    hahmo.setPosX(col);
                    hahmo.setPosY(row);
                    add(hahmo, col, row);
                } else if (arr[col] == '*') {
                    dict.put(new Pair<>(col, row), BOX);
                    add(new Box(), col, row);
                } else if (arr[col] == '.') {
                    dict.put(new Pair<>(col, row), TARGET);
                    add(new Target(), col, row);
                }
            }
        }
    }
}
