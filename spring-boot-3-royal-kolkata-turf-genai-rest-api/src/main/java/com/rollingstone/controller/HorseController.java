package com.rollingstone.controller;

import com.rollingstone.entity.Horse;
import com.rollingstone.services.HorseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/horses")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:4300, http://localhost:4400") // Allow requests from Angular app
public class HorseController {

    @Autowired
    private HorseService service;

    Logger logger  = LoggerFactory.getLogger("HorseController");

    @GetMapping
    public ResponseEntity<List<Horse>> getAllHorses() {
        return ResponseEntity.ok(service.getAllHorses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horse> getHorseById(@PathVariable Integer id) {
        Optional<Horse> horse = service.getHorseById(id);
        if (horse.isPresent()) {
            return ResponseEntity.ok(horse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Horse> createHorse(@RequestBody Horse horse) {
        logger.info("Horse Received :" + horse.toString());
        return ResponseEntity.ok(service.createHorse(horse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horse> updateHorse(@PathVariable Integer id, @RequestBody Horse horse) {
        try {
            return ResponseEntity.ok(service.updateHorse(id, horse));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorse(@PathVariable Integer id) {
        try {
            service.deleteHorse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
