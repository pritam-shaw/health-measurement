package com.health.measurement.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.measurement.model.MeasurementData;
import com.health.measurement.service.MeasurementService;

@RestController
public class MeasurementDataController {
    @Autowired
    MeasurementService service;

    @GetMapping("/measurementdatalist")
    public List<MeasurementData>getMeasurementDataList(){
        return service.get();
    }

    @GetMapping("/measurementdata/{id}")
    public MeasurementData getMeasurementData(@PathVariable int id) {
        return service.get(id);
    }
    @PostMapping("/measurementdata")
    public void addMeasurementDataList(@RequestBody MeasurementData prod){
        service.add(prod);
    }

}
