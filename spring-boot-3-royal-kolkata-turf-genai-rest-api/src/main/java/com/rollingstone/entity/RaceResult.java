package com.rollingstone.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "RACE_RESULTS")
public class RaceResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESULT_ID")
    private Integer resultId;

    @ManyToOne
    @JoinColumn(name = "RACE_ID", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "HORSE_ID", nullable = false)
    private Horse horse;

    @Column(name = "POSITION", nullable = false)
    private Integer position;

    @Column(name = "TIME_TAKEN", nullable = false)
    private LocalTime timeTaken;

    // Default constructor
    public RaceResult() {
    }

    // Full constructor
    public RaceResult(Integer resultId, Race race, Horse horse, Integer position, LocalTime timeTaken) {
        this.resultId = resultId;
        this.race = race;
        this.horse = horse;
        this.position = position;
        this.timeTaken = timeTaken;
    }

    // Getters and setters
    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public LocalTime getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(LocalTime timeTaken) {
        this.timeTaken = timeTaken;
    }

    // toString method
    @Override
    public String toString() {
        return "RaceResult{" +
                "resultId=" + resultId +
                ", race=" + (race != null ? race.getRaceName() : "null") +
                ", horse=" + (horse != null ? horse.getName() : "null") +
                ", position=" + position +
                ", timeTaken=" + timeTaken +
                '}';
    }
}
