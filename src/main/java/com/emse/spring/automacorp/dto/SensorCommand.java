package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.SensorType;

public record SensorCommand(Long id,String name, Double value, SensorType sensorType) {
}
