package com.kuehnenagel.coinrates.service;

import com.kuehnenagel.coinrates.dto.CurrencyDTO;
import com.kuehnenagel.coinrates.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyPriceService {
    private static final String ERROR_MESSAGE = "Something went wrong";

    private static final Logger log = LoggerFactory.getLogger(CurrencyPriceService.class);

    @Autowired
    JsonUtil jsonUtil;

    public String getCurrentPrice(String currency) {
        log.info("Retrieving current price");
        Optional<CurrencyDTO> currentPrice = jsonUtil.getCurrentPrice(currency);
        if (currentPrice.isPresent()) {
            log.info("Current price found");
            return Double.toString(currentPrice.get().getRateFloat());
        }
        return ERROR_MESSAGE;
    }
}
