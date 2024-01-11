package com.emse.spring.automacorp.Mappers;

import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoomMapper {
    public static Room of(RoomEntity room) {

        //define the Sensor currentTemperature from RoomEntity room data
        Sensor currentTemperature = new Sensor(
                room.getCurrentTemperature().getId(),
                room.getCurrentTemperature().getName(),
                room.getCurrentTemperature().getValue(),
                room.getCurrentTemperature().getSensorType()
        );

        //define the List<Window> windows from RoomEntity room data
        List<Window> windows = new ArrayList<>();
        for (WindowEntity windowEntity : room.getWindows()) {
            Sensor windowStatus = new Sensor(
                    windowEntity.getWindowStatus().getId(),
                    windowEntity.getWindowStatus().getName(),
                    windowEntity.getWindowStatus().getValue(),
                    windowEntity.getWindowStatus().getSensorType());
            Window window = new Window(windowEntity.getId(), windowEntity.getName(), windowStatus, windowEntity.getRoom().getId());
            windows.add(window);
        }

        //define the List<Heater> heaters from RoomEntity room data

        return new Room(
                room.getId(),
                room.getName(),
                room.getFloor(),
                currentTemperature,
                room.getTargetTemperature(),
                (Set<Window>) windows


        );
    }
}

