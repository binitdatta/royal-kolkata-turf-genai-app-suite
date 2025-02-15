package com.rollingstone.controller;

import com.rollingstone.entity.Betting;
import com.rollingstone.services.BettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bets")
public class BettingController {

    @Autowired
    private BettingService service;

    @GetMapping
    public ResponseEntity<List<Betting>> getAllBets() {
        return ResponseEntity.ok(service.getAllBets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Betting> getBetById(@PathVariable Integer id) {
        Optional<Betting> bet = service.getBetById(id);
        return bet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Betting> createBet(@RequestBody Betting bet) {
        return ResponseEntity.ok(service.createBet(bet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Betting> updateBet(@PathVariable Integer id, @RequestBody Betting bet) {
        try {
            return ResponseEntity.ok(service.updateBet(id, bet));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBet(@PathVariable Integer id) {
        try {
            service.deleteBet(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
