package com.health.measurement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.measurement.model.MeasurementData;


@Repository
public interface MeasurementRepo extends JpaRepository<MeasurementData, Integer>{

}