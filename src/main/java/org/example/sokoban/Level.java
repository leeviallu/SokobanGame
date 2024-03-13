package org.example.sokoban;

import java.io.Serializable;

/**
 * Luokka luo olion pelin tasosta.
 */
public class Level implements Serializable {
    /**
     * Tason pohjapiirrustus
     */
    private final String[] level;
    /**
     * Tason numero
     */
    private final int levelNumber;
    /**
     * Tason ennätysaika
     */
    private double recordTime;
    /**
     * Alustaa tason pohjapiirrustuksen, numeron ja ennätysajan
     */
    public Level(String[] level, int levelNumber) {
        this.level = level;
        this.levelNumber = levelNumber;
        this.recordTime = Double.POSITIVE_INFINITY;
    }
    /**
     * Palauttaa pohjapiirrustuksen
     */
    public String[] getLevel() {
        return level;
    }
    /**
     * Palauttaa kentän numeron
     */
    public int getLevelNumber() {
        return levelNumber;
    }
    /**
     * Palauttaa ennätysajan
     */
    public double getRecordTime() {
        return recordTime;
    }
    /**
     * Asettaa oliolle ennätysajan
     */
    public void setRecordTime(double recordTime) {
        this.recordTime = recordTime;
    }
}
