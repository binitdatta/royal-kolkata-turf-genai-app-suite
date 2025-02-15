package com.rollingstone.springaifunctions.model;

public class HorsesByCategoryBarchartData {
    private int category;
    private long totalRaces;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getTotalRaces() {
        return totalRaces;
    }

    public void setTotalRaces(long totalRaces) {
        this.totalRaces = totalRaces;
    }

    public HorsesByCategoryBarchartData() {
    }

    public HorsesByCategoryBarchartData(int category, long totalRaces) {
        this.category = category;
        this.totalRaces = totalRaces;
    }


}
