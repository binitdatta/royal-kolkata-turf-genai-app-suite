package com.rollingstone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ANNOUNCEMENTS")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNOUNCEMENT_ID")
    private Integer announcementId;

    @ManyToOne
    @JoinColumn(name = "RACE_ID", nullable = false)
    private Race race;

    @Column(name = "ANNOUNCEMENT_TEXT", columnDefinition = "TEXT", nullable = false)
    private String announcementText;

    @Column(name = "ANNOUNCEMENT_DATE", nullable = false)
    private LocalDateTime announcementDate;

    // Default constructor
    public Announcement() {
    }

    // Full constructor
    public Announcement(Integer announcementId, Race race, String announcementText, LocalDateTime announcementDate) {
        this.announcementId = announcementId;
        this.race = race;
        this.announcementText = announcementText;
        this.announcementDate = announcementDate;
    }

    // Getters and setters
    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getAnnouncementText() {
        return announcementText;
    }

    public void setAnnouncementText(String announcementText) {
        this.announcementText = announcementText;
    }

    public LocalDateTime getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(LocalDateTime announcementDate) {
        this.announcementDate = announcementDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId=" + announcementId +
                ", race=" + (race != null ? race.getRaceName() : "null") +
                ", announcementText='" + announcementText + '\'' +
                ", announcementDate=" + announcementDate +
                '}';
    }
}
