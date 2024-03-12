package org.example.sokoban;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import java.util.Dictionary;
import java.util.Hashtable;

public class Layout {
    private final int WALL = 1;
    private final int FLOOR = 2;
    private final int CHARACTER = 3;
    private final int BOX = 4;
    private final int TARGET = 5;
    private Dictionary<Pair<Integer, Integer>, Integer> mutableDict = new Hashtable<>();
    private final Dictionary<Pair<Integer, Integer>, Integer> layoutDict = new Hashtable<>();
    private GridPane gridpane = new GridPane();
    private Character character = new Character();
    private final String[] board;
    protected Level[] levelList;
    public Level[] getLevelList() {
        String[][] levels = {
                {
                        "#######",
                        "#. # .#",
                        "# $@$ #",
                        "#  #  #",
                        "#######"
                },
                {
                        "#####",
                        "#@  ##",
                        "#.$* #",
                        "#  # #",
                        "#    #",
                        "######"

                },
                {
                        "######",
                        "#@   ##",
                        "# $$  #",
                        "# #. .#",
                        "#     #",
                        "#######"
                },
                {
                        "  #### ",
                        "###  # ",
                        "#@ .$##",
                        "#   $ #",
                        "# #.  #",
                        "#     #",
                        "#######"
                },
                {
                        "#######",
                        "#@ ..##",
                        "# #$ ##",
                        "# #   #",
                        "#  $# #",
                        "#  *  #",
                        "#######"
                }

        };
        Level[] levelList = new Level[levels.length];
        for (int i = 0; i < levels.length; i++) {
            levelList[i] = new Level(levels[i], i+1);
        }
        return levelList;
    }

    public Dictionary<Pair<Integer, Integer>, Integer> getDict() {
        return mutableDict;
    }
    public void setDict(Dictionary<Pair<Integer, Integer>, Integer> dict) {
        this.mutableDict = dict;
    }
    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }
    public GridPane getBoard() {
        return gridpane;
    }
    public void setBoard(GridPane gridpane) {
        this.gridpane = gridpane;
    }

    public Layout() {
        levelList = getLevelList();
        board = levelList[0].getLevel();

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

    public Layout(int level) {
        levelList = getLevelList();
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
