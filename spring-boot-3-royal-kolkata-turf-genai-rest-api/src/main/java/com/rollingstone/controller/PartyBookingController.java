package com.rollingstone.controller;

import com.rollingstone.entity.PartyBooking;
import com.rollingstone.services.PartyBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/party-bookings")
public class PartyBookingController {

    @Autowired
    private PartyBookingService service;

    @GetMapping
    public ResponseEntity<List<PartyBooking>> getAllPartyBookings() {
        return ResponseEntity.ok(service.getAllPartyBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyBooking> getPartyBookingById(@PathVariable Integer id) {
        Optional<PartyBooking> booking = service.getPartyBookingById(id);
        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PartyBooking> createPartyBooking(@RequestBody PartyBooking booking) {
        return ResponseEntity.ok(service.createPartyBooking(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyBooking> updatePartyBooking(@PathVariable Integer id, @RequestBody PartyBooking booking) {
        try {
            return ResponseEntity.ok(service.updatePartyBooking(id, booking));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartyBooking(@PathVariable Integer id) {
        try {
            service.deletePartyBooking(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
