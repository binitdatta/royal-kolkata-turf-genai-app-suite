package com.rollingstone.services;

import com.rollingstone.entity.PartyBooking;
import com.rollingstone.repository.PartyBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartyBookingService {

    @Autowired
    private PartyBookingRepository repository;

    public List<PartyBooking> getAllPartyBookings() {
        return repository.findAll();
    }

    public Optional<PartyBooking> getPartyBookingById(Integer id) {
        return repository.findById(id);
    }

    public PartyBooking createPartyBooking(PartyBooking booking) {
        return repository.save(booking);
    }

    public PartyBooking updatePartyBooking(Integer id, PartyBooking booking) {
        if (repository.existsById(id)) {
            booking.setBookingId(id);
            return repository.save(booking);
        }
        throw new RuntimeException("PartyBooking not found with id: " + id);
    }

    public void deletePartyBooking(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("PartyBooking not found with id: " + id);
        }
    }
}
