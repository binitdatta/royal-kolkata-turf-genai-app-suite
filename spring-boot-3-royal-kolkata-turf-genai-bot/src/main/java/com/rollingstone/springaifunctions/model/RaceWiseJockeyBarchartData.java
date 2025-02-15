package com.rollingstone.springaifunctions.model;

public class RaceWiseJockeyBarchartData {
    private String jockey;
    private long raceParticipated;

    public String getJockey() {
        return jockey;
    }

    public void setJockey(String jockey) {
        this.jockey = jockey;
    }

    public long getRaceParticipated() {
        return raceParticipated;
    }

    public void setRaceParticipated(long raceParticipated) {
        this.raceParticipated = raceParticipated;
    }

    public RaceWiseJockeyBarchartData(String jockey, long raceParticipated) {
        this.jockey = jockey;
        this.raceParticipated = raceParticipated;
    }

    public RaceWiseJockeyBarchartData() {
    }
}
