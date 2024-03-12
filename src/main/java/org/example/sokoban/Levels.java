package org.example.sokoban;

import java.io.*;
import java.util.Arrays;

public class Levels {
    String[][] initialLevels = {
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
    private final String fName = "levels.txt";
    private Level[] levels;
    public Levels() {
        File f = new File(fName);
        if(f.exists() && !f.isDirectory()) {
            System.out.println("Tiedosto löytyi.");
            readFile();
        } else {
            System.out.println("Tiedostoa ei löytynyt.");
            levels = initLevels();
        }
    }

    public Level[] getLevels() {
        return levels;
    }
    public void setLevels(Level[] levels) {
        this.levels = levels;
    }

    public Level[] initLevels() {
        Level[] levelList = new Level[initialLevels.length];
        for (int i = 0; i < initialLevels.length; i++) {
            levelList[i] = new Level(initialLevels[i], i+1);
        }
        return levelList;
    }

    public void readFile() {
        ObjectInputStream rFile = null;
        Level[] rLevels;
        try {
            rFile = new ObjectInputStream(new FileInputStream(fName));
            rLevels = (Level[])rFile.readObject();
            levels = rLevels;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rFile != null) {
                    rFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeFile() {
        ObjectOutputStream wFile = null;
        try {
            wFile = new ObjectOutputStream(new FileOutputStream(fName));
            wFile.writeObject(levels);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (wFile != null) {
                    wFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleFile() {
        writeFile();
        readFile();
        for (Level lvl : levels) {
            System.out.println(lvl.getLevelNumber());
            System.out.println(Arrays.toString(lvl.getLevel()));
            System.out.println(lvl.getRecordTime());
        }
    }
}
