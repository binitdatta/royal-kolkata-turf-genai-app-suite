package com.rollingstone.model;

import java.math.BigDecimal;

public class TicketSalesByRace {

    private String race;

    private BigDecimal revenue;

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
}
