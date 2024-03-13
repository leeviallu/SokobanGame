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
     * @param level tason pohjapiirrustus
     * @param levelNumber tason numero
     */
    public Level(String[] level, int levelNumber) {
        this.level = level;
        this.levelNumber = levelNumber;
        this.recordTime = Double.POSITIVE_INFINITY;
    }
    /**
     * Palauttaa pohjapiirrustuksen
     * @return tason pohjapiirrustus
     */
    public String[] getLevel() {
        return level;
    }
    /**
     * Palauttaa tason numeron
     * @return tason numero
     */
    public int getLevelNumber() {
        return levelNumber;
    }
    /**
     * Palauttaa ennätysajan
     * @return ennätysaika
     */
    public double getRecordTime() {
        return recordTime;
    }
    /**
     * Asettaa tasolle ennätysajan
     * @param recordTime ennätysaika
     */
    public void setRecordTime(double recordTime) {
        this.recordTime = recordTime;
    }
}
