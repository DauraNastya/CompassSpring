package ru.appline.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompassModel implements Serializable {
    private static final CompassModel instance = new CompassModel();
    private final ArrayList<Compass> modelAsList;
    private final Map<String, Degrees> parsedModel;

    public CompassModel() {
        this.modelAsList = new ArrayList<Compass>();
        this.parsedModel = new HashMap<String, Degrees>();
    }

    public static CompassModel getInstance() {
        return instance;
    }

    public void addV1(ArrayList<Compass> compassPoints) {
        modelAsList.clear();
        modelAsList.addAll(compassPoints);
    }

    public void addV2(Map<String, String> compassPoints) {
        parsedModel.clear();
        parseDegrees(compassPoints);
    }

    public void parseDegrees(Map<String, String> compassPoints) {
        if (!compassPoints.isEmpty()) {
            String[] degrees;
            for (Map.Entry<String, String> compass : compassPoints.entrySet()) {
                degrees = compass.getValue().split("-");
                parsedModel.put(compass.getKey(), new Degrees(Integer.parseInt(degrees[0]), Integer.parseInt(degrees[1])));
            }
        }
    }

    public ArrayList<Compass> getAllList() {
        return modelAsList;
    }

    public Map<String, Degrees> getAllMap() {
        return parsedModel;
    }

    public String getSideByDegreeV1(int degree) {
        int startPoint = 0;
        int endPoint = 360;
        Degrees degrees;
        for (Compass compass : modelAsList) {
            degrees = compass.degrees;
            if (defineSide(degree, startPoint, endPoint, degrees)) {
                return compass.side;
            }
        }
        return "Сторона не найдена!";
    }

    public String getSideByDegreeV2(int degree) {
        int startPoint = 0;
        int endPoint = 360;
        Degrees degrees;
        for (Map.Entry<String, Degrees> compass : parsedModel.entrySet()) {
            degrees = compass.getValue();
            if (defineSide(degree, startPoint, endPoint, degrees)) {
                return compass.getKey();
            }
        }
        return "Сторона не найдена!";
    }

    private boolean defineSide(int degree, int startPoint, int endPoint, Degrees degrees) {
        int newStartDegree1;
        int newEndDegree1;
        int newStartDegree2;
        int newEndDegree2;
        if (degrees.getStartDegree() > degrees.getEndDegree()) {
            newStartDegree1 = degrees.getStartDegree();
            newEndDegree1 = endPoint;
            newStartDegree2 = startPoint;
            newEndDegree2 = degrees.getEndDegree();
            if (isIncludedInRange(degree, newStartDegree1, newEndDegree1) ||
                    isIncludedInRange(degree, newStartDegree2, newEndDegree2)) {
                return true;
            }
        }
        if (isIncludedInRange(degree, degrees.getStartDegree(), degrees.getEndDegree())) {
            return true;
        }
        return false;
    }

    private boolean isIncludedInRange(int degree, int startDegree, int endDegree) {
        return degree >= startDegree && degree <= endDegree;
    }
}