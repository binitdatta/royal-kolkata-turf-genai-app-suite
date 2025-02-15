package com.rollingstone.controller;

import com.rollingstone.entity.RaceWon;
import com.rollingstone.services.RaceWonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/races-won")
public class RaceWonController {

    @Autowired
    private RaceWonService service;

    @GetMapping
    public ResponseEntity<List<RaceWon>> getAllRacesWon() {
        return ResponseEntity.ok(service.getAllRacesWon());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceWon> getRaceWonById(@PathVariable Integer id) {
        Optional<RaceWon> raceWon = service.getRaceWonById(id);
        if (raceWon.isPresent()) {
            return ResponseEntity.ok(raceWon.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RaceWon> createRaceWon(@RequestBody RaceWon raceWon) {
        return ResponseEntity.ok(service.createRaceWon(raceWon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaceWon> updateRaceWon(@PathVariable Integer id, @RequestBody RaceWon raceWon) {
        try {
            return ResponseEntity.ok(service.updateRaceWon(id, raceWon));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaceWon(@PathVariable Integer id) {
        try {
            service.deleteRaceWon(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
