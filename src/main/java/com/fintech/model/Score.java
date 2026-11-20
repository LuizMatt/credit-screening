package com.fintech.model;

public class Score {
    private Integer points;
    private boolean available;

    public Score(Integer points, boolean available) {
        this.points = points;
        this.available = available;

    }

    // Getters
    public Integer getPoints() {
        return points;
    }

    public boolean getAvailable() {
        return available;
    }

    // Setters
    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
