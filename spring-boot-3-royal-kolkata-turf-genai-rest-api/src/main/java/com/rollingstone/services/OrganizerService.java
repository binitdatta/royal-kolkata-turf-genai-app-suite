package com.rollingstone.services;

import com.rollingstone.entity.Organizer;
import com.rollingstone.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository repository;

    public List<Organizer> getAllOrganizers() {
        return repository.findAll();
    }

    public Optional<Organizer> getOrganizerById(Integer id) {
        return repository.findById(id);
    }

    public Organizer createOrganizer(Organizer organizer) {
        return repository.save(organizer);
    }

    public Organizer updateOrganizer(Integer id, Organizer organizer) {
        if (repository.existsById(id)) {
            organizer.setOrganizerId(id);
            return repository.save(organizer);
        }
        throw new RuntimeException("Organizer not found with id: " + id);
    }

    public void deleteOrganizer(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Organizer not found with id: " + id);
        }
    }
}
