package com.rollingstone.services;

import com.rollingstone.entity.Betting;
import com.rollingstone.repository.BettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BettingService {

    @Autowired
    private BettingRepository repository;

    public List<Betting> getAllBets() {
        return repository.findAll();
    }

    public Optional<Betting> getBetById(Integer id) {
        return repository.findById(id);
    }

    public Betting createBet(Betting bet) {
        return repository.save(bet);
    }

    public Betting updateBet(Integer id, Betting bet) {
        if (repository.existsById(id)) {
            bet.setBetId(id);
            return repository.save(bet);
        }
        throw new RuntimeException("Bet not found with id: " + id);
    }

    public void deleteBet(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Bet not found with id: " + id);
        }
    }
}
