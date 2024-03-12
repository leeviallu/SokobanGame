package org.example.sokoban;

import java.io.Serializable;

public class Level implements Serializable {
    private String[] level;
    private int levelNumber;
    private int recordTime;

    public Level() {}
    public Level(String[] level, int levelNumber) {
        this.level = level;
        this.levelNumber = levelNumber;
        this.recordTime = 999999999;
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
    public int getRecordTime() {
        return recordTime;
    }
    public void setRecordTime(int recordTime) {
        this.recordTime = recordTime;
    }
}
