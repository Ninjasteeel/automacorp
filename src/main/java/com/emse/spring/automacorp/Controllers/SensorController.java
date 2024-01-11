package com.emse.spring.automacorp.Controllers;

import com.emse.spring.automacorp.Mappers.SensorMapper;
import com.emse.spring.automacorp.dao.*;
import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.SensorCommand;
import com.emse.spring.automacorp.model.SensorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
@RequestMapping("/api/sensors")
@Transactional
public class SensorController {
    private static final Logger logger = LogManager.getLogger(SensorController.class);

    private final SensorDao sensorDao;

    public SensorController(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @GetMapping
    public List<Sensor> findAll() {
        logger.info("Fetching all sensors.");
        List<Sensor> sensors = sensorDao.findAll()
                .stream()
                .map(SensorMapper::of)
                .sorted(Comparator.comparing(Sensor::name))
                .collect(Collectors.toList());
        logger.debug("Fetched {} sensors.", sensors.size());
        return sensors;
    }
    @GetMapping(path = "/{id}")
    public Sensor findById(@PathVariable Long id) {
        logger.info("Fetching sensor by ID: {}", id);
        return sensorDao.findById(id)
                .map(sensorEntity -> {
                    logger.debug("Sensor found with ID: {}", id);
                    return SensorMapper.of(sensorEntity);
                })
                .orElseGet(() -> {
                    logger.error("Sensor not found with ID: {}", id);
                    return null;
                });
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Sensor> update(@PathVariable Long id, @RequestBody SensorCommand sensor) {
        logger.info("Updating sensor with ID: {}", id);
        SensorEntity entity = sensorDao.findById(id).orElse(null);
        if (entity == null) {
            logger.warn("Sensor not found for update with ID: {}", id);
            return ResponseEntity.badRequest().build();
        }
        entity.setValue(sensor.value());
        entity.setName(sensor.name());
        entity.setSensorType(sensor.sensorType());
        logger.debug("Sensor updated: {}", entity);
        return ResponseEntity.ok(SensorMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Deleting sensor with ID: {}", id);
        sensorDao.deleteById(id);
        logger.debug("Sensor deleted with ID: {}", id);
    }
}

