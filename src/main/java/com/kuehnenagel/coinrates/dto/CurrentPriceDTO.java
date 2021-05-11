package com.kuehnenagel.coinrates.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPriceDTO {
    private BPICurrencyPriceDTO bpi;

    public BPICurrencyPriceDTO getBpi() {
        return bpi;
    }

    public void setBpi(BPICurrencyPriceDTO bpi) {
        this.bpi = bpi;
    }

    @Override
    public String toString() {
        return "CurrentPrice{" +
                "bpiCurrency=" + bpi +
                '}';
    }
}
