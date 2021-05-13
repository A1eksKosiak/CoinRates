package com.kuehnenagel.coinrates.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuehnenagel.coinrates.dto.CurrencyDTO;
import com.kuehnenagel.coinrates.dto.CurrentPriceDTO;
import com.kuehnenagel.coinrates.dto.HistoricalPriceDTO;
import com.kuehnenagel.coinrates.dto.SupportedCurrencyDTO;
import com.kuehnenagel.coinrates.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JsonUtil {
    private static final String SUPPORTED_CURRENCIES_URL = "https://api.coindesk.com/v1/bpi/supported-currencies.json";
    private static final String CURRENT_PRICE_URL = "https://api.coindesk.com/v1/bpi/currentprice/";
    private static final String FILE_EXTENSION_URL = ".json";
    private static final String HISTORICAL_PRICE_URL = "https://api.coindesk.com/v1/bpi/historical/close.json?start=";
    private static final String CONCATENATE_END_URL = "&end=";
    private static final String CONCATENATE_CURRENCY_URL = "&currency=";
    private static final int THIRTY = 30;
    private static final String EUROPE_TALLINN = "Europe/Tallinn";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final Logger log = LoggerFactory.getLogger(ValidationService.class);

    public List<SupportedCurrencyDTO> getSupportedCurrencies() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<SupportedCurrencyDTO> supportedCurrencyDTOList = new ArrayList<>();
        try {
            supportedCurrencyDTOList = objectMapper.readValue(new URL(SUPPORTED_CURRENCIES_URL), new TypeReference<List<SupportedCurrencyDTO>>() {
            });
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return supportedCurrencyDTOList;
    }

    public Optional<CurrencyDTO> getCurrentPrice(String currencyCode) {
        ObjectMapper objectMapper = new ObjectMapper();
        CurrentPriceDTO currentPriceDTO = new CurrentPriceDTO();
        try {
            currentPriceDTO = objectMapper.readValue(new URL(CURRENT_PRICE_URL + currencyCode
                    + FILE_EXTENSION_URL), CurrentPriceDTO.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        List<CurrencyDTO> currencies = currentPriceDTO.getBpi().getCurrencies();
        return currencies.stream()
                .filter(currencyDTO -> currencyDTO.getCode().equalsIgnoreCase(currencyCode))
                .findFirst();
    }

    public HistoricalPriceDTO getHistoricalPrice(String currencyCode) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate start = LocalDate.now(ZoneId.of(EUROPE_TALLINN)).minusDays(THIRTY);
        LocalDate end = LocalDate.now(ZoneId.of(EUROPE_TALLINN));

        HistoricalPriceDTO historicalPriceDTO = new HistoricalPriceDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            historicalPriceDTO = objectMapper.readValue(new URL(HISTORICAL_PRICE_URL + start.format(formatter) + CONCATENATE_END_URL
                    + end.format(formatter) + CONCATENATE_CURRENCY_URL + currencyCode), HistoricalPriceDTO.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return historicalPriceDTO;
    }
}
