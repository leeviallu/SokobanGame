package org.example.sokoban;

import java.io.*;

/**
 * Luokka luo olion, jossa on pelissä esiintyvät tasot.
 */
public class InitLevels implements HandleFile {
    /**
     * Tiedoston nimi
     */
    public static final String fName = "levels.txt";
    /**
     * Peliin kovakoodatut kentät
     */
    static String[][] hardcodedLevels = {
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
    /**
     * Lista tasoista
     */
    public static Level[] levels;
    /**
     * Alustaja hakee listan tasoista lähtökohtaisesti tiedostosta, mutta jos tiedostoa ei ole, luodaan tasot manuaalisesti
     */
    public InitLevels() {
        File f = new File(InitLevels.fName);
        if(f.exists() && !f.isDirectory()) {
            levels = InitLevels.readFile();
        } else {
            levels = new Level[hardcodedLevels.length];
            for (int i = 0; i < hardcodedLevels.length; i++) {
                levels[i] = new Level(hardcodedLevels[i], i+1);
            }
        }
    }
    /**
     * Luetaan tasot tiedostosta
     */
    public static Level[] readFile() {
        return HandleFile.readFile();
    }
    /**
     * Kirjoitetaan tiedostoon tasot
     */
    public static void writeFile(Level[] levels) {
        HandleFile.writeFile(levels);
    }
}
