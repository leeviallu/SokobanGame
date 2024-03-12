package org.example.sokoban;

import java.io.Serializable;

public class Level implements Serializable {
    private String[] level;
    private int levelNumber;
    private double recordTime;

    public Level() {}
    public Level(String[] level, int levelNumber) {
        this.level = level;
        this.levelNumber = levelNumber;
        this.recordTime = Double.POSITIVE_INFINITY;
    }
    public String[] getLevel() {
        return level;
    }
    public void setLevel(String[] level) {
        this.level = level;
    }
    public int getLevelNumber() {
        return levelNumber;
    }
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
    public double getRecordTime() {
        return recordTime;
    }
    public void setRecordTime(double recordTime) {
        this.recordTime = recordTime;
    }
}
