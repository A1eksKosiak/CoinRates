package com.kuehnenagel.coinrates.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class BPIHistoricalPriceDTO {
    private Map<String, Double> dailyRates = new HashMap<>();

    @JsonAnySetter
    public void setDynamicProperty(String date, Double rate) {
        dailyRates.put(date, rate);
    }

    public Map<String, Double> getDailyRates() {
        return dailyRates;
    }

    public void setDailyRates(Map<String, Double> dailyRates) {
        this.dailyRates = dailyRates;
    }

    @Override
    public String toString() {
        return "BPIHistorical{" +
                "dailyRates=" + dailyRates +
                '}';
    }
}
