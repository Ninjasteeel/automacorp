package com.emse.spring.automacorp.Controllers;

import com.emse.spring.automacorp.Mappers.SensorMapper;
import com.emse.spring.automacorp.Mappers.WindowMapper;
import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.RoomDaoImpl;
import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.dto.WindowCommand;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = { "http://localhost:5173" }, maxAge = 3600)@RestController
@RequestMapping("/api/windows")
@Transactional
public class WindowController {
    private final WindowDao windowDao;

    public WindowController(WindowDao windowDao) {
        this.windowDao = windowDao;
    }

    @GetMapping
    public List<Window> findAllWindows() {
        return windowDao.findAll().stream()
                .map(WindowMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Window> findWindowById(@PathVariable Long id) {
        return windowDao.findById(id)
                .map(windowEntity -> ResponseEntity.ok(WindowMapper.toDTO(windowEntity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Window> createWindow(@RequestBody WindowCommand windowCommand) {
        SensorEntity sensorEntity = SensorMapper.createSensorEntity(windowCommand.getWindowStatus());
        WindowEntity windowEntity = createWindowEntity(windowCommand, sensorEntity);
        Window createdWindow = WindowMapper.toDTO(windowEntity);
        return ResponseEntity.ok(createdWindow);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Window> updateWindow(@PathVariable Long id, @RequestBody WindowCommand windowCommand) {
        return windowDao.findById(id)
                .map(existingWindowEntity -> {
                    SensorEntity sensorEntity = SensorMapper.createSensorEntity(windowCommand.getWindowStatus());
                    updateWindowEntity(existingWindowEntity, windowCommand, sensorEntity);
                    Window updatedWindow = WindowMapper.toDTO(existingWindowEntity);
                    return ResponseEntity.ok(updatedWindow);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWindow(@PathVariable Long id) {
        return windowDao.findById(id)
                .map(existingWindowEntity -> {
                    windowDao.delete(existingWindowEntity);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Helper method to create WindowEntity from WindowCommand
    private WindowEntity createWindowEntity(WindowCommand windowCommand, SensorEntity sensorEntity) {RoomDao roomDao;

        RoomEntity roomEntity = new RoomEntity(); // Replace with your logic to get or create RoomEntity

        return new WindowEntity(
                windowCommand.getName(),
                sensorEntity,
                roomEntity
        );
    }

    // Helper method to update WindowEntity with WindowCommand
    private void updateWindowEntity(WindowEntity windowEntity, WindowCommand windowCommand, SensorEntity sensorEntity) {
        windowEntity.setName(windowCommand.getName());
        windowEntity.setWindowStatus(sensorEntity);
        // Update other properties if needed
    }
}
