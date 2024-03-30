package ru.appline.logic;

import java.io.Serializable;

public class Compass implements Serializable {
    String side;
    Degrees degrees;

    public Compass() {
        super();
    }

    public Compass(String side, Degrees degrees) {
        this.side = side;
        this.degrees = degrees;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Degrees getDegrees() {
        return degrees;
    }

    public void setDegrees(Degrees degrees) {
        this.degrees = degrees;
    }
}
