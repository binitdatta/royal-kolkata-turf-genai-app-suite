package com.rollingstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "RACE_PARTICIPANTS")
public class RaceParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARTICIPATION_ID")
    private Integer participationId;

    @ManyToOne
    @JoinColumn(name = "RACE_ID", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "HORSE_ID", nullable = false)
    private Horse horse;

    @ManyToOne
    @JoinColumn(name = "JOCKEY_ID", nullable = false)
    private Jockey jockey;

    @Column(name = "STARTING_POSITION", nullable = false)
    private Integer startingPosition;

    @Column(name = "HANDICAP", nullable = false)
    private Double handicap;

    // Default constructor
    public RaceParticipant() {
    }

    // Full constructor
    public RaceParticipant(Integer participationId, Race race, Horse horse, Jockey jockey, Integer startingPosition, Double handicap) {
        this.participationId = participationId;
        this.race = race;
        this.horse = horse;
        this.jockey = jockey;
        this.startingPosition = startingPosition;
        this.handicap = handicap;
    }

    // Getters and setters
    public Integer getParticipationId() {
        return participationId;
    }

    public void setParticipationId(Integer participationId) {
        this.participationId = participationId;
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

    public Jockey getJockey() {
        return jockey;
    }

    public void setJockey(Jockey jockey) {
        this.jockey = jockey;
    }

    public Integer getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Integer startingPosition) {
        this.startingPosition = startingPosition;
    }

    public Double getHandicap() {
        return handicap;
    }

    public void setHandicap(Double handicap) {
        this.handicap = handicap;
    }

    // toString method
    @Override
    public String toString() {
        return "RaceParticipant{" +
                "participationId=" + participationId +
                ", race=" + (race != null ? race.getRaceName() : "null") +
                ", horse=" + (horse != null ? horse.getName() : "null") +
                ", jockey=" + (jockey != null ? jockey.getFullName() : "null") +
                ", startingPosition=" + startingPosition +
                ", handicap=" + handicap +
                '}';
    }
}
