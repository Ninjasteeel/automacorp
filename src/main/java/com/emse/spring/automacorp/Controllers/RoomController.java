package com.emse.spring.automacorp.Controllers;

import com.emse.spring.automacorp.Mappers.RoomMapper;
import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.RoomCommand;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT
})
@RestController
@RequestMapping("/api/rooms")
@Transactional

public class RoomController {

    private final RoomDao roomDao;
    private final WindowDao windowDao;


    private final SensorDao sensorDao;

    private static Logger logger = LogManager.getLogger(RoomController.class);

    public RoomController(RoomDao roomDao, WindowDao windowDao,  SensorDao sensorDao) {
        this.roomDao = roomDao;
        this.windowDao = windowDao;

        this.sensorDao = sensorDao;
    }

    /**
     * Often the first function called during the acces to this API
     * @return List of all rooms
     */
    @GetMapping
    public List<Room> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomMapper::of)
                .sorted(Comparator.comparing(Room::name))
                .collect(Collectors.toList());
    }


    @GetMapping(path = "/{id}")
    public Room findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomMapper::of).orElse(null);
    }


    @PostMapping
    public ResponseEntity<Room> create(@RequestBody RoomCommand room) {


        SensorEntity currentTemperature = new SensorEntity(SensorType.TEMPERATURE,room.name() + " current temperature");
        currentTemperature.setValue(room.currentTemperature().getValue());
        sensorDao.save(currentTemperature);

        RoomEntity entity = new RoomEntity( room.getName(), currentTemperature,room.getFloor(), room.targetTemperature() );
        RoomEntity saved = roomDao.save(entity);
        return ResponseEntity.ok(RoomMapper.of(saved));
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody RoomCommand room) {
        if (id==null && room==null){
            logger.fatal("There are no id nor room...");
            return ResponseEntity.badRequest().build();
        }
        else if (id==null || room==null) {
            logger.error("There is a missing argument between id and room.");
            return ResponseEntity.badRequest().build();
        }
        else {
            RoomEntity entity = roomDao.findById(id).orElse(null);
            if (entity == null) {
                logger.warn("The given room id (" + id +")doesn't exist.");
                return ResponseEntity.badRequest().build();
            }
            logger.debug("Beginning of room " + id + "'s update");

            SensorEntity currentTemperature = sensorDao.findById(entity.getCurrentTemperature().getId()).orElse(null);
            if (currentTemperature == null) {
                return ResponseEntity.badRequest().build();
            }
            currentTemperature.setValue(room.currentTemperature().getValue());

            entity.setFloor(room.floor());
            entity.setName(room.name());
            entity.setTargetTemperature(room.targetTemperature());
            logger.info("The room " + id + " has successfully been modified.");
            return ResponseEntity.ok(RoomMapper.of(entity));
        }
    }


    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        sensorDao.deleteByRoomId(id);
        windowDao.deleteWindowsByRoomId(id);
        roomDao.deleteById(id);
    }



}