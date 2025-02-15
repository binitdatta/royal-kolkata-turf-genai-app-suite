package com.rollingstone.springaifunctions.model;

public class CategoryWiseHandicapBarchartData {
    private String race;
    private long totalBets;

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public long getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(long totalBets) {
        this.totalBets = totalBets;
    }

    public CategoryWiseHandicapBarchartData(String race, long totalBets) {
        this.race = race;
        this.totalBets = totalBets;
    }

    public CategoryWiseHandicapBarchartData() {
    }
}
