package com.rollingstone.controller;

import com.rollingstone.entity.RaceResult;
import com.rollingstone.services.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/race-results")
public class RaceResultController {

    @Autowired
    private RaceResultService service;

    @GetMapping
    public ResponseEntity<List<RaceResult>> getAllRaceResults() {
        return ResponseEntity.ok(service.getAllRaceResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceResult> getRaceResultById(@PathVariable Integer id) {
        Optional<RaceResult> result = service.getRaceResultById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RaceResult> createRaceResult(@RequestBody RaceResult result) {
        return ResponseEntity.ok(service.createRaceResult(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaceResult> updateRaceResult(@PathVariable Integer id, @RequestBody RaceResult result) {
        try {
            return ResponseEntity.ok(service.updateRaceResult(id, result));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaceResult(@PathVariable Integer id) {
        try {
            service.deleteRaceResult(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
