package ru.appline.logic;

import java.io.Serializable;

public class Degrees implements Serializable {
    private int startDegree;
    private int endDegree;

    public Degrees() {
        super();
    }

    public Degrees(int startDegree, int endDegree) {
        this.startDegree = startDegree;
        this.endDegree = endDegree;
    }

    public int getStartDegree() {
        return startDegree;
    }

    public void setStartDegree(int startDegree) {
        this.startDegree = startDegree;
    }

    public int getEndDegree() {
        return endDegree;
    }

    public void setEndDegree(int endDegree) {
        this.endDegree = endDegree;
    }
}