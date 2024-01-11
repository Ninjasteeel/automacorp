package com.emse.spring.automacorp.dto;

import java.util.Set;

public record RoomCommand(Long id, String name, Integer floor, SensorCommand currentTemperature, Double targetTemperature, Set<WindowCommand> windows) {
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getFloor() {
        return floor;
    }

    public SensorCommand getCurrentTemperature() {
        return currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public Set<WindowCommand> getWindows() {
        return windows;
    }
}

