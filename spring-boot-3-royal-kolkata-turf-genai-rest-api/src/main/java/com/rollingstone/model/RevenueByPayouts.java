package com.rollingstone.model;

import java.math.BigDecimal;

public class RevenueByPayouts {

    private String race;

    private BigDecimal totalPayouts;

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public BigDecimal getTotalPayouts() {
        return totalPayouts;
    }

    public void setTotalPayouts(BigDecimal totalPayouts) {
        this.totalPayouts = totalPayouts;
    }
}
