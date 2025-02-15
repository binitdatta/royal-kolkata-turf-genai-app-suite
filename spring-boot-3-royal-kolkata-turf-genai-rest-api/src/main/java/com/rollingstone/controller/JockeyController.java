package com.rollingstone.controller;

import com.rollingstone.entity.Jockey;
import com.rollingstone.services.JockeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jockeys")
public class JockeyController {

    @Autowired
    private JockeyService service;

    @GetMapping
    public ResponseEntity<List<Jockey>> getAllJockeys() {
        return ResponseEntity.ok(service.getAllJockeys());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jockey> getJockeyById(@PathVariable Integer id) {
        Optional<Jockey> jockey = service.getJockeyById(id);
        if (jockey.isPresent()) {
            return ResponseEntity.ok(jockey.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Jockey> createJockey(@RequestBody Jockey jockey) {
        return ResponseEntity.ok(service.createJockey(jockey));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jockey> updateJockey(@PathVariable Integer id, @RequestBody Jockey jockey) {
        try {
            return ResponseEntity.ok(service.updateJockey(id, jockey));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJockey(@PathVariable Integer id) {
        try {
            service.deleteJockey(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
