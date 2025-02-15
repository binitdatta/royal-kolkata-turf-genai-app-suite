package com.rollingstone.controller;

import com.rollingstone.entity.BettingPayout;
import com.rollingstone.services.BettingPayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/betting-payouts")
public class BettingPayoutController {

    @Autowired
    private BettingPayoutService service;

    @GetMapping
    public ResponseEntity<List<BettingPayout>> getAllPayouts() {
        return ResponseEntity.ok(service.getAllPayouts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BettingPayout> getPayoutById(@PathVariable Integer id) {
        Optional<BettingPayout> payout = service.getPayoutById(id);
        return payout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BettingPayout> createPayout(@RequestBody BettingPayout payout) {
        return ResponseEntity.ok(service.createPayout(payout));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BettingPayout> updatePayout(@PathVariable Integer id, @RequestBody BettingPayout payout) {
        try {
            return ResponseEntity.ok(service.updatePayout(id, payout));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayout(@PathVariable Integer id) {
        try {
            service.deletePayout(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
