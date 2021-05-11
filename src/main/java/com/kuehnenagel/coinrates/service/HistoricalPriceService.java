package com.kuehnenagel.coinrates.service;

import com.kuehnenagel.coinrates.dto.HistoricalPriceDTO;
import com.kuehnenagel.coinrates.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class HistoricalPriceService {
    private static final Logger log = LoggerFactory.getLogger(HistoricalPriceService.class);
    private HistoricalPriceDTO historicalPriceDTO = null;

    @Autowired
    JsonUtil jsonUtil;


    public String getMinHistoricalPrice(String currency) {
        getHistoricalPrice(currency);
        log.info("Retrieving min price from the historical price");
        Optional<Map.Entry<String, Double>> minEntry = historicalPriceDTO.getBpi().getDailyRates()
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue());
        if (minEntry.isPresent()) {
            return Double.toString(minEntry.get().getValue());
        }
        return "";

    }

    public String getMaxHistoricalPrice(String currency) {
        getHistoricalPrice(currency);
        log.info("Retrieving max price from the historical price");
        Optional<Map.Entry<String, Double>> maxEntry = historicalPriceDTO.getBpi().getDailyRates()
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        if (maxEntry.isPresent()) {
            return Double.toString(maxEntry.get().getValue());
        }
        return "";
    }

    private void getHistoricalPrice(String currency) {
        if (historicalPriceDTO != null) {
            log.info("Historical price already retrieved");
            return;
        }
        log.info("Retrieving the historical price");
        try {
            historicalPriceDTO = jsonUtil.getHistoricalPrice(currency);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }

}
