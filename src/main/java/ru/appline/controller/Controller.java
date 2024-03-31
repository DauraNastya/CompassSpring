package ru.appline.controller;

import javafx.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.appline.logic.Compass;
import ru.appline.logic.CompassModel;
import ru.appline.logic.Degrees;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class Controller {
    private static final CompassModel compassModel = CompassModel.getInstance();

    /*
        Пример файла json: 'src\main\resources\compass_v1_with_ArrayList.json'
     */
    @PostMapping(value = "/createCompassAsList", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArrayList<Compass>> createCompassAsList(@RequestBody ArrayList<Compass> newCompassPoints) {
        compassModel.addV1(newCompassPoints);
        return ResponseEntity.ok(newCompassPoints);
    }

    /*
        Пример файла json: 'src\main\resources\compass_v2_with_Map.json'
     */
    @PostMapping(value = "/createCompassAsMap", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> createCompassAsMap(@RequestBody Map<String, String> newCompassPoints) {
        compassModel.addV2(newCompassPoints);
        return ResponseEntity.ok(newCompassPoints);
    }

    @GetMapping(value = "/getAllList", produces = "application/json")
    public ArrayList<Compass> getAllList() {
        return compassModel.getAllList();
    }

    @GetMapping(value = "/getAllMap", produces = "application/json")
    public Map<String, Degrees> getAllMap() {
        return compassModel.getAllMap();
    }

    /*
        {
            "Degree": 10
        }
    */
    @GetMapping(value = "/getSideV1", consumes = "application/json", produces = "application/json")
    public Pair<String, String> getSideByDegreeV1(@RequestBody Map<String, Integer> degree) {
        return new Pair<String, String>("Side", compassModel.getSideByDegreeV1(degree.get("Degree")));
    }

    /*
        {
            "Degree": 10
        }
    */
    @GetMapping(value = "/getSideV2", consumes = "application/json", produces = "application/json")
    public Pair<String, String> getSideByDegreeV2(@RequestBody Map<String, Integer> degree) {
        return new Pair<String, String>("Side", compassModel.getSideByDegreeV2(degree.get("Degree")));
    }
}