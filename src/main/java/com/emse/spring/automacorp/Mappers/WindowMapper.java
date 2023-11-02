package com.emse.spring.automacorp.Mappers;

import com.emse.spring.automacorp.dto.Window;
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
}


