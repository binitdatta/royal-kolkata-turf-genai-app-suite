package com.rollingstone.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "RACES")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RACE_ID", nullable = false)
    private Integer raceId;

    @Column(name="RACE_NAME", nullable = false)
    private String raceName;

    @Column(name="LOCATION", nullable = false)
    private String location;

    @Column(name="RACE_DATE", nullable = false)
    private LocalDate raceDate;

    @Column(name="RACE_TIME", nullable = false)
    private LocalTime raceTime;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private HorseCategory category;

    @ManyToOne
    @JoinColumn(name = "ANNOUNCED_BY")
    private Organizer announcedBy;

    // Default constructor
    public Race() {
    }

    // Full constructor
    public Race(Integer raceId, String raceName, String location, LocalDate raceDate, LocalTime raceTime,
                HorseCategory category, Organizer announcedBy) {
        this.raceId = raceId;
        this.raceName = raceName;
        this.location = location;
        this.raceDate = raceDate;
        this.raceTime = raceTime;
        this.category = category;
        this.announcedBy = announcedBy;
    }

    // Getters and setters
    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(LocalDate raceDate) {
        this.raceDate = raceDate;
    }

    public LocalTime getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(LocalTime raceTime) {
        this.raceTime = raceTime;
    }

    public HorseCategory getCategory() {
        return category;
    }

    public void setCategory(HorseCategory category) {
        this.category = category;
    }

    public Organizer getAnnouncedBy() {
        return announcedBy;
    }

    public void setAnnouncedBy(Organizer announcedBy) {
        this.announcedBy = announcedBy;
    }

    // toString method
    @Override
    public String toString() {
        return "Race{" +
                "raceId=" + raceId +
                ", raceName='" + raceName + '\'' +
                ", location='" + location + '\'' +
                ", raceDate=" + raceDate +
                ", raceTime=" + raceTime +
                ", category=" + (category != null ? category.getCategoryName() : "null") +
                ", announcedBy=" + (announcedBy != null ? announcedBy.getFullName() : "null") +
                '}';
    }
}
