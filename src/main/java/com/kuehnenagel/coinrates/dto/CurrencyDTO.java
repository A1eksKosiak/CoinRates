package com.kuehnenagel.coinrates.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDTO {
    private String code;
    @JsonProperty("rate_float")
    private double rateFloat;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRateFloat() {
        return rateFloat;
    }

    public void setRateFloat(double rateFloat) {
        this.rateFloat = rateFloat;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", rate_float=" + rateFloat +
                '}';
    }
}
