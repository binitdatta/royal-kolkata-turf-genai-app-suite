package com.rollingstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "RACES_WON")
public class RaceWon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RACE_WIN_ID", nullable = false)
    private Integer raceWinId;

    @ManyToOne
    @JoinColumn(name = "HORSE_ID", nullable = false)
    private Horse horse;

    @ManyToOne
    @JoinColumn(name = "RACE_ID", nullable = false)
    private Race race;

    @Column(name = "POSITION", nullable = false)
    private Integer position;

    // Default constructor
    public RaceWon() {
    }

    // Full constructor
    public RaceWon(Integer raceWinId, Horse horse, Race race, Integer position) {
        this.raceWinId = raceWinId;
        this.horse = horse;
        this.race = race;
        this.position = position;
    }

    // Getters and setters
    public Integer getRaceWinId() {
        return raceWinId;
    }

    public void setRaceWinId(Integer raceWinId) {
        this.raceWinId = raceWinId;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    // toString method
    @Override
    public String toString() {
        return "RaceWon{" +
                "raceWinId=" + raceWinId +
                ", horse=" + (horse != null ? horse.getName() : "null") +
                ", race=" + (race != null ? race.getRaceName() : "null") +
                ", position=" + position +
                '}';
    }
}
