package com.rollingstone.services;

import com.rollingstone.entity.RaceParticipant;
import com.rollingstone.repository.RaceParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceParticipantService {

    @Autowired
    private RaceParticipantRepository repository;

    public List<RaceParticipant> getAllRaceParticipants() {
        return repository.findAll();
    }

    public Optional<RaceParticipant> getRaceParticipantById(Integer id) {
        return repository.findById(id);
    }

    public RaceParticipant createRaceParticipant(RaceParticipant participant) {
        return repository.save(participant);
    }

    public RaceParticipant updateRaceParticipant(Integer id, RaceParticipant participant) {
        if (repository.existsById(id)) {
            participant.setParticipationId(id);
            return repository.save(participant);
        }
        throw new RuntimeException("RaceParticipant not found with id: " + id);
    }

    public void deleteRaceParticipant(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("RaceParticipant not found with id: " + id);
        }
    }
}
