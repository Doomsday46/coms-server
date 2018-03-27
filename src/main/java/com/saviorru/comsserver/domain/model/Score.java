package com.saviorru.comsserver.domain.model;

public class Score {

    private int pointsFirstSide;
    private int pointsSecondSide;

    public Score(){
        this.pointsFirstSide = 0;
        this.pointsSecondSide = 0;
    }

    public Score(int pointsFirstSide, int pointsSecondSide) throws IllegalArgumentException {
        if(pointsFirstSide < 0 || pointsSecondSide < 0) throw new IllegalArgumentException();
        this.pointsFirstSide = pointsFirstSide;
        this.pointsSecondSide = pointsSecondSide;
    }

    public void setPoints(int pointsFirstSide, int pointsSecondSide) throws IllegalArgumentException {
        if(pointsFirstSide < 0 || pointsSecondSide < 0) throw new IllegalArgumentException();
        this.pointsFirstSide = pointsFirstSide;
        this.pointsSecondSide = pointsSecondSide;
    }

    public int getPointsFirstSide() {
        return this.pointsFirstSide;
    }

    public int getPointsSecondSide() {
        return this.pointsSecondSide;
    }
}
