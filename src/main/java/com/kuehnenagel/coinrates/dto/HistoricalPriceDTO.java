package com.kuehnenagel.coinrates.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalPriceDTO {
    private BPIHistoricalPriceDTO bpi;

    public BPIHistoricalPriceDTO getBpi() {
        return bpi;
    }

    public void setBpi(BPIHistoricalPriceDTO bpi) {
        this.bpi = bpi;
    }

    @Override
    public String toString() {
        return "HistoricalPrice{" +
                "bpi=" + bpi +
                '}';
    }
}
