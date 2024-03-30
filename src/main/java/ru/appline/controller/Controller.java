package ru.appline.controller;

import javafx.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.appline.logic.Compass;
import ru.appline.logic.CompassModel;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class Controller {
    private static final CompassModel compassModel = CompassModel.getInstance();

    /*
        Пример файла json: 'src\main\resources\compass.json'
     */
    @PostMapping(value = "/createCompass", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArrayList<Compass>> createCompass(@RequestBody ArrayList<Compass> newCompassPoints) {
        compassModel.add(newCompassPoints);
        return ResponseEntity.ok(newCompassPoints);
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ArrayList<Compass> getAll() {
        return compassModel.getAll();
    }

    /*
        {
            "Degree": 10
        }
    */
    @GetMapping(value = "/getSide", consumes = "application/json", produces = "application/json")
    public Pair<String, String> getSideByDegree(@RequestBody Map<String, Integer> degree) {
        return new Pair<String, String>("Side", compassModel.getSideByDegree(degree.get("Degree")));
    }
}