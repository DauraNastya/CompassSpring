package ru.appline.logic;

import java.io.Serializable;
import java.util.ArrayList;

public class CompassModel implements Serializable {
    private static final CompassModel instance = new CompassModel();
    private final ArrayList<Compass> model;

    public CompassModel() {
        this.model = new ArrayList<Compass>();
    }

    public static CompassModel getInstance() {
        return instance;
    }

    public void add(ArrayList<Compass> compassPoints) {
        model.clear();
        model.addAll(compassPoints);
    }

    public ArrayList<Compass> getAll() {
        return model;
    }

    public String getSideByDegree(int degree) {
        int startPoint = 0;
        int endPoint = 360;
        int newStartDegree1;
        int newEndDegree1;
        int newStartDegree2;
        int newEndDegree2;
        Degrees degrees;
        for (Compass compass : model) {
            degrees = compass.degrees;

            if (degrees.getStartDegree() > degrees.getEndDegree()) {
                newStartDegree1 = degrees.getStartDegree();
                newEndDegree1 = endPoint;
                newStartDegree2 = startPoint;
                newEndDegree2 = degrees.getEndDegree();
                if (isIncludedInRange(degree, newStartDegree1, newEndDegree1) ||
                        isIncludedInRange(degree, newStartDegree2, newEndDegree2)) {
                    return compass.side;
                }
            }
            if (isIncludedInRange(degree, degrees.getStartDegree(), degrees.getEndDegree())) {
                return compass.side;
            }
        }
        return "Сторона не найдена!";
    }

    private boolean isIncludedInRange(int degree, int startDegree, int endDegree) {
        return degree >= startDegree && degree <= endDegree;
    }
}