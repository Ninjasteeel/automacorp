package com.emse.spring.automacorp.dto;
public class WindowCommand {

    private String name;
    private SensorCommand windowStatus;
    private Long roomId;

    // Constructors, getters, and setters

    public WindowCommand() {
    }

    public WindowCommand(String name, SensorCommand windowStatus, Long roomId) {
        this.name = name;
        this.windowStatus = windowStatus;
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorCommand getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(SensorCommand windowStatus) {
        this.windowStatus = windowStatus;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
