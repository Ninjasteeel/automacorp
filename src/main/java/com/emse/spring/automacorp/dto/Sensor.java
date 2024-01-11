package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.SensorType;

public record Sensor(Long id, String name, Double value, SensorType sensorType) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Double value() {
        return value;
    }

    @Override
    public SensorType sensorType() {
        return sensorType;
    }
}

