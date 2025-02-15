package com.rollingstone.services;

import com.rollingstone.entity.BettingPayout;
import com.rollingstone.repository.BettingPayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BettingPayoutService {

    @Autowired
    private BettingPayoutRepository repository;

    public List<BettingPayout> getAllPayouts() {
        return repository.findAll();
    }

    public Optional<BettingPayout> getPayoutById(Integer id) {
        return repository.findById(id);
    }

    public BettingPayout createPayout(BettingPayout payout) {
        return repository.save(payout);
    }

    public BettingPayout updatePayout(Integer id, BettingPayout payout) {
        if (repository.existsById(id)) {
            payout.setPayoutId(id);
            return repository.save(payout);
        }
        throw new RuntimeException("Payout not found with id: " + id);
    }

    public void deletePayout(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Payout not found with id: " + id);
        }
    }
}
