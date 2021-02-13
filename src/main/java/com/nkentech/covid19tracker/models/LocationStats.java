package com.nkentech.covid19tracker.models;

import java.util.Objects;

public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPrevDay;

    public LocationStats(){}

    public LocationStats(String state, String country, int latestTotalCases, int diffFromPrevDay) {
        this.state = state;
        this.country = country;
        this.latestTotalCases = latestTotalCases;
        this.diffFromPrevDay = diffFromPrevDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationStats)) return false;
        LocationStats that = (LocationStats) o;
        return getLatestTotalCases() == that.getLatestTotalCases() && getDiffFromPrevDay() == that.getDiffFromPrevDay() && Objects.equals(getState(), that.getState()) && Objects.equals(getCountry(), that.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getCountry(), getLatestTotalCases(), getDiffFromPrevDay());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocationStats{");
        sb.append("state='").append(state).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", latestTotalCases=").append(latestTotalCases);
        sb.append(", diffFromPrevDay=").append(diffFromPrevDay);
        sb.append('}');
        return sb.toString();
    }
}
