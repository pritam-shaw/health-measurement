package com.health.measurement.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.health.measurement.model.MeasurementData;


@Repository
public interface MeasurementRepo extends JpaRepository<MeasurementData, Integer>{
    @Query(value = "SELECT * FROM (SELECT * FROM measurement_data ORDER BY id DESC LIMIT 20) AS subquery ORDER BY id ASC", nativeQuery = true)
    List<MeasurementData> findLast20Entries();
}