package org.example.sokoban;

import java.io.*;

import static org.example.sokoban.InitLevels.fName;

public interface HandleFile {
    static Level[] readFile() {
        ObjectInputStream rFile = null;
        Level[] rLevels = new Level[0];
        try {
            rFile = new ObjectInputStream(new FileInputStream(fName));
            rLevels = (Level[])rFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Tiedoston lukemisessa ilmeni virhe.");
        } finally {
            try {
                if (rFile != null) {
                    rFile.close();
                }
            } catch (IOException e) {
                System.out.println("Tiedoston sulkemisessa ilmeni virhe.");
            }
        }
        return rLevels;
    }

    static void writeFile(Level[] levels) {
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
                System.out.println("Tiedoston sulkemisessa ilmeni virhe.");
            }
        }
    };
}
