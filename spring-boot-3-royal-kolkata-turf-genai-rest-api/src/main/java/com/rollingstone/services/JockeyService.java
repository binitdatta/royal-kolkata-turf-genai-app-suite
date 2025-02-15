package com.rollingstone.services;

import com.rollingstone.entity.Jockey;
import com.rollingstone.repository.JockeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JockeyService {

    @Autowired
    private JockeyRepository repository;

    public List<Jockey> getAllJockeys() {
        return repository.findAll();
    }

    public Optional<Jockey> getJockeyById(Integer id) {
        return repository.findById(id);
    }

    public Jockey createJockey(Jockey jockey) {
        return repository.save(jockey);
    }

    public Jockey updateJockey(Integer id, Jockey jockey) {
        if (repository.existsById(id)) {
            jockey.setJockeyId(id);
            return repository.save(jockey);
        }
        throw new RuntimeException("Jockey not found with id: " + id);
    }

    public void deleteJockey(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Jockey not found with id: " + id);
        }
    }
}
