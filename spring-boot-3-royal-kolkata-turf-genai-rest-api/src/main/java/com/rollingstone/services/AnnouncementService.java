package com.rollingstone.services;

import com.rollingstone.entity.Announcement;
import com.rollingstone.entity.Role;
import com.rollingstone.entity.User;
import com.rollingstone.repository.AnnouncementRepository;
import com.rollingstone.repository.RoleRepository;
import com.rollingstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    // CRUD for Announcements
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Optional<Announcement> getAnnouncementById(Integer id) {
        return announcementRepository.findById(id);
    }

    public Announcement createAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public void deleteAnnouncement(Integer id) {
        announcementRepository.deleteById(id);
    }



}
