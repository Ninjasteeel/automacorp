package com.emse.spring.automacorp.Mappers;

import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class RoomMapper {
    public static Room of(RoomEntity roomEntity) {
        Set<Window> windows = roomEntity.getWindows()
                .stream()
                .map(WindowMapper::toDTO)
                .collect(Collectors.toSet());

        // Check if currentTemperature is not null before mapping
        Sensor currentTemperature = roomEntity.getCurrentTemperature() != null
                ? SensorMapper.of(roomEntity.getCurrentTemperature())
                : null;

        return new Room(
                roomEntity.getId(),
                roomEntity.getName(),
                roomEntity.getFloor(),
                currentTemperature,
                roomEntity.getTargetTemperature(),
                windows
        );
    }
    public static Room updateRoomEntity(RoomEntity roomEntity, Room room) {
        // Update the RoomEntity properties based on the Room information
        roomEntity.setName(room.getName());
        roomEntity.setFloor(room.getFloor());
        roomEntity.setTargetTemperature(room.getTargetTemperature());

        // You may need to update other properties based on your data model

        // Update the windows (assuming windows are part of the RoomEntity)
        Set<WindowEntity> updatedWindows = room.getWindows()
                .stream()
                .map(window -> {
                    // Implement the mapping logic from Window to WindowEntity
                    // You can use a WindowMapper or implement it inline
                    return createWindowEntity(window);
                })
                .collect(Collectors.toSet());

        roomEntity.setWindows(updatedWindows);

        return RoomMapper.of(roomEntity);
    }
    public static WindowEntity createWindowEntity(Window window) {
        WindowEntity windowEntity = new WindowEntity();
        windowEntity.setId(window.id());
        windowEntity.setName(window.name());

        // Assuming there's a corresponding method in WindowMapper to convert Window to WindowEntity
        SensorEntity windowStatusEntity = WindowMapper.createWindowStatusEntity(window.windowStatus());
        windowEntity.setWindowStatus(windowStatusEntity);

        // You may need to set other properties based on your data model

        return windowEntity;
    }

    public static RoomEntity createRoomEntity(Room room) {
        RoomEntity roomEntity = new RoomEntity();
        // Set the properties of RoomEntity based on the Room information
        roomEntity.setName(room.getName());
        roomEntity.setFloor(room.getFloor());
        roomEntity.setTargetTemperature(room.getTargetTemperature());

        // You may need to set other properties based on your data model

        // Create the windows (assuming windows are part of the RoomEntity)
        Set<WindowEntity> windowEntities = room.getWindows()
                .stream()
                .map(WindowMapper::createWindowEntity)
                .collect(Collectors.toSet());

        roomEntity.setWindows(windowEntities);

        return roomEntity;
    }
}


