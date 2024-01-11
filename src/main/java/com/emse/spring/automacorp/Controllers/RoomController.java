package com.emse.spring.automacorp.Controllers;

import com.emse.spring.automacorp.Mappers.RoomMapper;
import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.model.RoomEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<Room> findAllRooms() {
        return roomDao.findAll().stream()
                .map(RoomMapper::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findRoomById(@PathVariable Long id) {
        return roomDao.findById(id)
                .map(roomEntity -> ResponseEntity.ok(RoomMapper.of(roomEntity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        RoomEntity roomEntity = RoomMapper.createRoomEntity(room);
        Room createdRoom = RoomMapper.of(roomDao.save(roomEntity));
        return ResponseEntity.ok(createdRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomDao.findById(id)
                .map(existingRoomEntity -> {
                    RoomMapper.updateRoomEntity(existingRoomEntity, room);
                    Room updatedRoom = RoomMapper.of(roomDao.save(existingRoomEntity));
                    return ResponseEntity.ok(updatedRoom);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoom(@PathVariable Long id) {
        return roomDao.findById(id)
                .map(existingRoomEntity -> {
                    roomDao.delete(existingRoomEntity);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
