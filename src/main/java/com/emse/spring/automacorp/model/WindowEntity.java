package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

@Entity// (1)
@Table(name = "SP_WINDOW")// (2)
public class WindowEntity {
    @Id// (3)
    @GeneratedValue
    private Long id;

    @Column(nullable=false, length=255) // (4)
    private String name;

    @OneToOne// (5)
    private SensorEntity windowStatus;

    @ManyToOne
    private RoomEntity room;

    public WindowEntity() {
    }

    public WindowEntity(String name, SensorEntity sensor,RoomEntity room) {
        this.windowStatus = sensor;
        this.name = name;
        this.room=room;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public Long getRoomId() { return this.room.getId(); }

    public void setName(String name) {
        this.name = name;
    }

    public SensorEntity getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(SensorEntity windowStatus) {
        this.windowStatus = windowStatus;
    }
}
