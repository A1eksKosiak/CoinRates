package com.kuehnenagel.coinrates.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupportedCurrencyDTO {
    private String currency;
    private String country;

    public SupportedCurrencyDTO() {
    }

    public SupportedCurrencyDTO(String currency, String country) {
        this.currency = currency;
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "SupportedCurrency{" +
                "currency='" + currency + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}