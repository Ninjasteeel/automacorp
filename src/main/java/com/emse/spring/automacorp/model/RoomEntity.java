package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

import java.util.Set;

import static java.util.Set.*;

@Entity // (1).
@Table(name = "SP_ROOM") // (2).
public class RoomEntity {
    @Id // (3).
    @GeneratedValue
    private Long id;

    @Column(nullable=false)  // (4).
    private String name;

    @Column(name = "FLOOR",nullable=false) // (5)
    private Integer floor;

    @OneToOne// (5)
    private SensorEntity current_temperature;

    @Column(name = "TARGET_TEMPERATURE") // (5)
    private Double target_temperature;

    @OneToMany(mappedBy = "room")
    private Set<WindowEntity> windows = of();



    public Set<WindowEntity> getWindows() {
        return windows;
    }
    public RoomEntity() { // (8).
    }

    public RoomEntity(String name, SensorEntity current_temperature,Integer floor) {
        this.current_temperature = current_temperature;
        this.name = name;
        this.floor=floor;


    }


    public RoomEntity(String name, SensorEntity current_temperature,Integer floor,Double target_temperature) {
        this.current_temperature = current_temperature;
        this.name = name;
        this.floor=floor;
        this.target_temperature=target_temperature;

    }

    public Long getId() { // (10).
        return id;
    }

    public Double getTargetTemperature() { return target_temperature; }

    public SensorEntity getCurrentTemperature() { return current_temperature; }

    public String getName() { return name; }

    public Integer getFloor() { return floor; }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setCurrentTemperature(SensorEntity currentTemperature) {
        this.current_temperature = currentTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.target_temperature = targetTemperature;
    }

    public void setWindows(Set<WindowEntity> windows) {
        this.windows = windows;
    }



}
