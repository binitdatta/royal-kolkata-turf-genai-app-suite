package com.rollingstone.springaifunctions.model;

public class MonthlyRevenueByPartyBookingsLineColumnChartData {
    private int month;
    private long year;

    private long totalRevenue;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public MonthlyRevenueByPartyBookingsLineColumnChartData() {
    }

    public MonthlyRevenueByPartyBookingsLineColumnChartData(int month, long year, long totalRevenue) {
        this.month = month;
        this.year = year;
        this.totalRevenue = totalRevenue;
    }


}
