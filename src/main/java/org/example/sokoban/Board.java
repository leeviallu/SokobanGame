package org.example.sokoban;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends GridPane {
    private int rows = 10;
    private int columns = 10;
    Character hahmo = new Character();


    public Board() {
        initBoard();
    }

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        initBoard();
    }

    public void initBoard() {
        String[] board = {"#######", "# @ # #", "#   $ #", "#   $ #", "# ..  #", "#  *  #", "#######"};
        for (int i = 0; i < board.length; i++) {
            char[] arr = board[i].toCharArray();
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == '#') {
                    add(new Wall(), i, j);
                    System.out.println("Seinä");
                } else if (arr[j] == ' ') {
                    add(new Floor(), i, j);
                    System.out.println("Lattia");
                } else if (arr[j] == '@') {
                    hahmo.setPosX(i);
                    hahmo.setPosY(j);
                    add(hahmo, i, j);
                    System.out.println("Hahmo");
                } else if (arr[j] == '.') {
                    add(new Box(), i, j);
                    System.out.println("Laatikko");
                } else if (arr[j] == '$') {
                    System.out.println("Kohde");
                }
                System.out.println(i + " " + j);
            }
        }
    }
}
