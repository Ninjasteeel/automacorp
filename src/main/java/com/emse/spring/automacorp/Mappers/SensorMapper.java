package com.emse.spring.automacorp.Mappers;

import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.SensorCommand;
import com.emse.spring.automacorp.model.SensorEntity;

public class SensorMapper {

    public static SensorEntity createSensorEntity(SensorCommand sensorCommand) {
        SensorEntity sensorEntity = new SensorEntity();
        // You need to set properties of SensorEntity based on SensorCommand
        // For example:
        sensorEntity.setName(sensorCommand.getName());
        sensorEntity.setValue(sensorCommand.getValue());
        sensorEntity.setSensorType(sensorCommand.getSensorType());
        return sensorEntity;
    }
    public static SensorEntity createSensorEntityFromSensor(Sensor sensor) {
        SensorEntity sensorEntity = new SensorEntity();
        // Set the properties of SensorEntity based on the Sensor information
        sensorEntity.setId(sensor.id());
        sensorEntity.setName(sensor.name());
        sensorEntity.setValue(sensor.value());
        sensorEntity.setSensorType(sensor.sensorType());

        // Set other properties of SensorEntity if needed

        return sensorEntity;
    }
    public static Sensor of(SensorEntity sensorEntity) {
        return new Sensor(
                sensorEntity.getId(),
                sensorEntity.getName(),
                sensorEntity.getValue(),
                sensorEntity.getSensorType()
        );
    }

}

