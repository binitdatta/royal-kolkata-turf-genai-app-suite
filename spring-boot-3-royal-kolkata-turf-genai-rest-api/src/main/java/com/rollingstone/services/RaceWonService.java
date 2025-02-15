package com.rollingstone.services;

import com.rollingstone.entity.RaceWon;
import com.rollingstone.repository.RaceWonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceWonService {

    @Autowired
    private RaceWonRepository repository;

    public List<RaceWon> getAllRacesWon() {
        return repository.findAll();
    }

    public Optional<RaceWon> getRaceWonById(Integer id) {
        return repository.findById(id);
    }

    public RaceWon createRaceWon(RaceWon raceWon) {
        return repository.save(raceWon);
    }

    public RaceWon updateRaceWon(Integer id, RaceWon raceWon) {
        if (repository.existsById(id)) {
            raceWon.setRaceWinId(id);
            return repository.save(raceWon);
        }
        throw new RuntimeException("RaceWon record not found with id: " + id);
    }

    public void deleteRaceWon(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("RaceWon record not found with id: " + id);
        }
    }
}
