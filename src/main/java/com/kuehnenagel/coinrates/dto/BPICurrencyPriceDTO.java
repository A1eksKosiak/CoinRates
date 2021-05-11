package com.kuehnenagel.coinrates.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;
import java.util.List;

public class BPICurrencyPriceDTO {
    private List<CurrencyDTO> currencies = new ArrayList<>();

    @JsonAnySetter
    public void setDynamicProperty(String name, CurrencyDTO currencyDTO) {
        currencies.add(currencyDTO);
    }

    public List<CurrencyDTO> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDTO> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "BPICurrency{" +
                "profiles=" + currencies +
                '}';
    }
}
