package com.emse.spring.automacorp.Mapper;

import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.Mappers.RoomMapper;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import com.emse.spring.automacorp.model.WindowEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

class RoomMapperTest {

    @Test
    void shouldMapRoom() {
        // Arrange
        SensorEntity currentTemperature = new SensorEntity(SensorType.TEMPERATURE, "Room temperature");
        currentTemperature.setId(1L);
        currentTemperature.setValue(24.2);

        RoomEntity roomEntity = new RoomEntity("Room", currentTemperature, 1);
        roomEntity.setId(1L);
        roomEntity.setTargetTemperature(22.0);

        SensorEntity windowStatus = new SensorEntity(SensorType.STATUS, "Window status");
        windowStatus.setId(2L);
        windowStatus.setValue(0.0);

        WindowEntity windowEntity = new WindowEntity("Window 1", windowStatus, roomEntity);
        windowEntity.setId(2L);
        roomEntity.setWindows(Set.of(windowEntity));

        // Act
        Room room = RoomMapper.of(roomEntity);

        // Assert
        Room expectedRoom = new Room(
                1L,
                "Room",
                1,
                new Sensor(1L, "Room temperature", 24.2, SensorType.TEMPERATURE),
                22.0,
                Set.of(new Window(
                        2L,
                        "Window 1",
                        new Sensor(2L, "Window status", 0.0, SensorType.STATUS),
                        1L
                ))
        );
        Assertions.assertThat(room).usingRecursiveAssertion().isEqualTo(expectedRoom);
    }
}
