package com.kuehnenagel.coinrates.service;

import com.kuehnenagel.coinrates.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ValidationService {
    private static final String ERROR_MESSAGE_FOR_NULL = "Input should not be null";
    private static final String ERROR_MESSAGE_UNSUPPORTED_CURRENCY = "The provided currency is not supported by the CoinDesk API";

    private static final Logger log = LoggerFactory.getLogger(ValidationService.class);

    @Autowired
    JsonUtil jsonUtil;

    public void validate(String currency) throws IllegalArgumentException {
        log.info("Validating currency");
        if (currency == null) {
            log.warn(ERROR_MESSAGE_FOR_NULL);
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_NULL);
        }
        try {
            if (!isSupportedCurrency(currency)) {
                log.warn(ERROR_MESSAGE_UNSUPPORTED_CURRENCY);
                throw new IllegalArgumentException(ERROR_MESSAGE_UNSUPPORTED_CURRENCY);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            //todo check if we need to return a message to the user
        }
    }

    private boolean isSupportedCurrency(String currency) throws IOException {
        log.info("Checking if currency is supported");
        return jsonUtil.getSupportedCurrencies().stream()
                .anyMatch(c -> c.getCurrency().equals(currency.toUpperCase()));
    }
}
