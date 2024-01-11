package com.emse.spring.automacorp.dto;

public record Window(Long id, String name, Sensor windowStatus, Long roomId) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Sensor windowStatus() {
        return windowStatus;
    }

    @Override
    public Long roomId() {
        return roomId;
    }
}
