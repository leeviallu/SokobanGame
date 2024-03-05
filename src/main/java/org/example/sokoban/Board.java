package org.example.sokoban;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends GridPane {
    private int rows = 10;
    private int columns = 10;

    public Board() {
        initBoard();
    }

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < rows; i ++) {
            if (i % 2 == 0) {
                for (int j = 1; j < columns; j += 2) {
                    add(new Floor(Color.WHITE), j - 1, i);
                    add(new Floor(Color.LIGHTGRAY), j, i);
                }
            } else {
                for (int j = 1; j < 10; j += 2) {
                    add(new Floor(Color.WHITE), j, i);
                    add(new Floor(Color.LIGHTGRAY), j-1, i);
                }
            }
        }
    }
}
