package com.rollingstone.controller;

import com.rollingstone.entity.Announcement;
import com.rollingstone.entity.Role;
import com.rollingstone.entity.User;
import com.rollingstone.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnnouncementController {

    @Autowired
    private AnnouncementService service;

    // Announcements
    @GetMapping("/announcements")
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        return ResponseEntity.ok(service.getAllAnnouncements());
    }

    @PostMapping("/announcements")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        return ResponseEntity.ok(service.createAnnouncement(announcement));
    }

    // Roles

}
