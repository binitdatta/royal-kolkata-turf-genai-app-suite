package com.rollingstone.services;

import com.rollingstone.entity.Race;
import com.rollingstone.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    @Autowired
    private RaceRepository repository;

    public List<Race> getAllRaces() {
        return repository.findAll();
    }

    public Optional<Race> getRaceById(Integer id) {
        return repository.findById(id);
    }

    public Race createRace(Race race) {
        return repository.save(race);
    }

    public Race updateRace(Integer id, Race race) {
        if (repository.existsById(id)) {
            race.setRaceId(id);
            return repository.save(race);
        }
        throw new RuntimeException("Race not found with id: " + id);
    }

    public void deleteRace(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Race not found with id: " + id);
        }
    }
}
