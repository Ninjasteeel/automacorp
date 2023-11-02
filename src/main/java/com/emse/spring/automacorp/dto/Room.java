package com.emse.spring.automacorp.dto;

import java.util.Set;

public record Room(Long id, String name, Integer floor, Sensor currentTemperature, Double targetTemperature,Set<Window> windows) {
}

