package com.emse.spring.automacorp.Mappers;

import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;

public class WindowMapper {
    public static Window toDTO(WindowEntity windowEntity) {
        return new Window(
                windowEntity.getId(),
                windowEntity.getName(),
                SensorMapper.of(windowEntity.getWindowStatus()),
                windowEntity.getRoomId()
        );
    }
    public static SensorEntity createWindowStatusEntity(Sensor windowStatus) {
        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setId(windowStatus.id());
        sensorEntity.setName(windowStatus.name());
        sensorEntity.setValue(windowStatus.value());
        sensorEntity.setSensorType(windowStatus.sensorType());

        // You may need to set other properties based on your data model

        return sensorEntity;
    }
    public static WindowEntity createWindowEntity(Window window) {
        WindowEntity windowEntity = new WindowEntity();
        // Set the properties of WindowEntity based on the Window information
        windowEntity.setName(window.name());

        // Assuming Sensor information is present in the Window DTO
        if (window.windowStatus() != null) {
            SensorEntity windowStatusEntity = SensorMapper.createSensorEntityFromSensor(window.windowStatus());
            windowEntity.setWindowStatus(windowStatusEntity);
        }

        // Set other properties of WindowEntity if needed

        return windowEntity;
    }

}


