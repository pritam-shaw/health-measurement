package com.health.measurement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MeasurementData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float voltage;
    private Float current;
    private Float temperature;

    public MeasurementData() {} 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getVoltage() {
        return this.voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    public Float getCurrent() {
        return this.current;
    }

    public void setCurrent(Float current) {
        this.current = current;
    }


    public Float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }
    @Override
    public String toString() {
        return "MeasurementData [id=" + id + ", ";
    }


}
