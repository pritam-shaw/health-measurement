package com.health.measurement.model;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MeasurementData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Nullable
    private Float acsCurrent;
    @Nullable
    private Float temperature;
    @Nullable
    private Float pzemVoltage;
    @Nullable
    private Float pzemCurrent;
    @Nullable
    private Float power;
    @Nullable
    private Float energy;
    @Nullable
    private Float frequency;
    @Nullable
    private Float powerFactor;

    public MeasurementData() {} 
    

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public @Nullable Float getAcsCurrent() {
        return acsCurrent;
    }


    public void setAcsCurrent(@Nullable Float acsCurrent) {
        this.acsCurrent = acsCurrent;
    }


    public @Nullable Float getTemperature() {
        return temperature;
    }


    public void setTemperature(@Nullable Float temperature) {
        this.temperature = temperature;
    }


    public @Nullable Float getPzemVoltage() {
        return pzemVoltage;
    }


    public void setPzemVoltage(@Nullable Float pzemVoltage) {
        this.pzemVoltage = pzemVoltage;
    }


    public @Nullable Float getPzemCurrent() {
        return pzemCurrent;
    }


    public void setPzemCurrent(@Nullable Float pzemCurrent) {
        this.pzemCurrent = pzemCurrent;
    }


    public @Nullable Float getPower() {
        return power;
    }


    public void setPower(@Nullable Float power) {
        this.power = power;
    }


    public @Nullable Float getEnergy() {
        return energy;
    }


    public void setEnergy(@Nullable Float energy) {
        this.energy = energy;
    }


    public @Nullable Float getFrequency() {
        return frequency;
    }


    public void setFrequency(@Nullable Float frequency) {
        this.frequency = frequency;
    }


    public @Nullable Float getPowerFactor() {
        return powerFactor;
    }


    public void setPowerFactor(@Nullable Float powerFactor) {
        this.powerFactor = powerFactor;
    }


    @Override
    public String toString() {
        return "MeasurementData [id=" + id + ", ";
    }


}
