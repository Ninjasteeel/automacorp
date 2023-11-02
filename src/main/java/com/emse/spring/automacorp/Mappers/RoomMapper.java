package com.emse.spring.automacorp.Mappers;

import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.model.RoomEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class RoomMapper {
    public static Room of(RoomEntity roomEntity) {
        Set<Window> windows = roomEntity.getWindows()
                .stream()
                .map(WindowMapper::toDTO)
                .collect(Collectors.toSet());

        return new Room(
                roomEntity.getId(),
                roomEntity.getName(),
                roomEntity.getFloor(),
                SensorMapper.of(roomEntity.getCurrentTemperature()),
                roomEntity.getTargetTemperature(),
                windows
        );
    }
}

