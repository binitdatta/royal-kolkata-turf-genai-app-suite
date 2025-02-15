package com.rollingstone.services;

import com.rollingstone.entity.RaceResult;
import com.rollingstone.repository.RaceResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceResultService {

    @Autowired
    private RaceResultRepository repository;

    public List<RaceResult> getAllRaceResults() {
        return repository.findAll();
    }

    public Optional<RaceResult> getRaceResultById(Integer id) {
        return repository.findById(id);
    }

    public RaceResult createRaceResult(RaceResult result) {
        return repository.save(result);
    }

    public RaceResult updateRaceResult(Integer id, RaceResult result) {
        if (repository.existsById(id)) {
            result.setResultId(id);
            return repository.save(result);
        }
        throw new RuntimeException("RaceResult not found with id: " + id);
    }

    public void deleteRaceResult(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("RaceResult not found with id: " + id);
        }
    }
}
