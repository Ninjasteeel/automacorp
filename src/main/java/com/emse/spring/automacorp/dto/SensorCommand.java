package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.SensorType;

public record SensorCommand(String name, Double value, SensorType sensorType,Long id) {public String getName() {
    return name;
}

    public Double getValue() {
        return value;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

}
