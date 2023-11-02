package com.emse.spring.automacorp.Mappers;

import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.model.SensorEntity;

public class SensorMapper {
    public static Sensor of(SensorEntity sensorEntity) {
        return new Sensor(
                sensorEntity.getId(),
                sensorEntity.getName(),
                sensorEntity.getValue(),
                sensorEntity.getSensorType()
        );
    }
}

