package com.rollingstone.springaifunctions.model;

public class HorsesByWinsBarchartData {
    private String horseName;
    private long wins;

    public String getHorseName() {
        return horseName;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public HorsesByWinsBarchartData() {
    }

    public HorsesByWinsBarchartData(String horseName, long wins) {
        this.horseName = horseName;
        this.wins = wins;
    }


}
