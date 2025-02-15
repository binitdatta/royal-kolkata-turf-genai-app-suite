package com.rollingstone.controller;

import com.rollingstone.entity.RaceParticipant;
import com.rollingstone.services.RaceParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/race-participants")
public class RaceParticipantController {

    @Autowired
    private RaceParticipantService service;

    @GetMapping
    public ResponseEntity<List<RaceParticipant>> getAllRaceParticipants() {
        return ResponseEntity.ok(service.getAllRaceParticipants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceParticipant> getRaceParticipantById(@PathVariable Integer id) {
        Optional<RaceParticipant> participant = service.getRaceParticipantById(id);
        if (participant.isPresent()) {
            return ResponseEntity.ok(participant.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RaceParticipant> createRaceParticipant(@RequestBody RaceParticipant participant) {
        return ResponseEntity.ok(service.createRaceParticipant(participant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaceParticipant> updateRaceParticipant(@PathVariable Integer id, @RequestBody RaceParticipant participant) {
        try {
            return ResponseEntity.ok(service.updateRaceParticipant(id, participant));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaceParticipant(@PathVariable Integer id) {
        try {
            service.deleteRaceParticipant(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
