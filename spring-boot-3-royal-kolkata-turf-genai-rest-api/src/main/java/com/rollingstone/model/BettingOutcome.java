package com.rollingstone.model;

public class BettingOutcome {
    private String outcome;
    private Long totalBets;

    // Getters and setters
    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Long getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(Long totalBets) {
        this.totalBets = totalBets;
    }
}
