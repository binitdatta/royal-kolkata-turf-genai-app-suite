package com.rollingstone.springaifunctions.model;

public class HistogramData {
    private int timeBin;
    private long count;

    public int getTimeBin() {
        return timeBin;
    }

    public void setTimeBin(int timeBin) {
        this.timeBin = timeBin;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    // Getters and Setters

    public HistogramData(int timeBin, long count) {
        this.timeBin = timeBin;
        this.count = count;
    }

    public HistogramData() {
    }
}
