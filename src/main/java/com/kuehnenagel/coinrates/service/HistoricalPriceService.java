package com.kuehnenagel.coinrates.service;

import com.kuehnenagel.coinrates.dto.HistoricalPriceDTO;
import com.kuehnenagel.coinrates.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class HistoricalPriceService {
    private static final String ERROR_MESSAGE = "Something went wrong";
    private static final Logger log = LoggerFactory.getLogger(HistoricalPriceService.class);
    private HistoricalPriceDTO historicalPriceDTO = null;

    @Autowired
    JsonUtil jsonUtil;

    public String getMinHistoricalPrice(String currency) {
        checkHistoricalPrice(currency);
        log.debug("Retrieving min price from the historical price");
        Optional<Map.Entry<String, Double>> minEntry = historicalPriceDTO.getBpi().getDailyRates()
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue());
        if (minEntry.isPresent()) {
            return Double.toString(minEntry.get().getValue());
        }
        return ERROR_MESSAGE;
    }

    public String getMaxHistoricalPrice(String currency) {
        checkHistoricalPrice(currency);
        log.debug("Retrieving max price from the historical price");
        Optional<Map.Entry<String, Double>> maxEntry = historicalPriceDTO.getBpi().getDailyRates()
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        if (maxEntry.isPresent()) {
            return Double.toString(maxEntry.get().getValue());
        }
        return ERROR_MESSAGE;
    }

    private void checkHistoricalPrice(String currency) {
        if (historicalPriceDTO != null) {
            log.debug("Historical price already retrieved");
            return;
        }
        log.debug("Retrieving the historical price");
        historicalPriceDTO = jsonUtil.getHistoricalPrice(currency);
    }
}