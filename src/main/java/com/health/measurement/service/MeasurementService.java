package com.health.measurement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.measurement.model.MeasurementData;
import com.health.measurement.repository.MeasurementRepo;

@Service
public class MeasurementService {

    @Autowired    
    MeasurementRepo repo;

    public List<MeasurementData> get(){
        return repo.findAll();
    }
    public MeasurementData get(int id) {

        return repo.findById(id).get();
    }
    public void add(MeasurementData prod){
        repo.save(prod);
    }
    public void update(MeasurementData prod) {
        repo.save(prod);
    }
    public void deleteProduce(int prodId) {   
        repo.deleteById(prodId);
    }
}
