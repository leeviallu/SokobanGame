package org.example.sokoban;

import java.io.*;

/**
 * Luokka luo olion, jossa on pelissä esiintyvät tasot.
 */
public class Levels implements HandleFile {
    /**
     * Peliin manuaalisesti syötetyt kentät
     */
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
    /**
     * Tiedoston nimi
     */
    private final String fName = "levels.txt";
    /**
     * Lista tasoista
     */
    private Level[] levels;
    /**
     * Alustaja hakee listan tasoista lähtökohtaisesti tiedostosta, mutta jos tiedostoa ei ole, luodaan tasot manuaalisesti
     */
    public Levels() {
        File f = new File(fName);
        if(f.exists() && !f.isDirectory()) {
            readFile();
        } else {
            levels = initLevels();
        }
    }
    /**
     * Palauttaa listan tasoista
     * @return tasot listana
     */
    public Level[] getLevels() {
        return levels;
    }
    /**
     * Tasojen luominen manuaalisesti
     * @return tasot listana
     */
    public Level[] initLevels() {
        Level[] levels = new Level[initialLevels.length];
        for (int i = 0; i < initialLevels.length; i++) {
            levels[i] = new Level(initialLevels[i], i+1);
        }
        return levels;
    }
    /**
     * Luetaan tasot tiedostosta
     */
    public void readFile() {
        ObjectInputStream rFile = null;
        Level[] rLevels;
        try {
            rFile = new ObjectInputStream(new FileInputStream(fName));
            rLevels = (Level[])rFile.readObject();
            levels = rLevels;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Tiedoston lukemisessa ilmeni virhe.");
        } finally {
            try {
                if (rFile != null) {
                    rFile.close();
                }
            } catch (IOException e) {
                System.out.println("Tiedoston lukemisessa ilmeni virhe.");
            }
        }
    }
    /**
     * Kirjoitetaan tiedostoon tasot
     */
    public void writeFile() {
        ObjectOutputStream wFile = null;
        try {
            wFile = new ObjectOutputStream(new FileOutputStream(fName));
            wFile.writeObject(levels);
        } catch (Exception e) {
            System.out.println("Tiedoston kirjoittamisessa ilmeni virhe.");
        } finally {
            try {
                if (wFile != null) {
                    wFile.close();
                }
            } catch (IOException e) {
                System.out.println("Tiedoston kirjoittamisessa ilmeni virhe.");
            }
        }
    }
}
