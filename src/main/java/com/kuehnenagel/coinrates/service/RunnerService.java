package com.kuehnenagel.coinrates.service;

import com.kuehnenagel.coinrates.utils.IOCommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RunnerService {
    private static final String WELCOME_MESSAGE = "Welcome to the CoinRates application!";
    private static final String CURRENCY_SELECT_MESSAGE = "Select the currency";
    private static final String EXIT_PHRASE = "EXIT";
    private static final String CURRENT_RATE_MESSAGE = "The current Bitcoin rate is ";
    private static final String MIN_RATE_MESSAGE = "The lowest Bitcoin rate in the last 30 days was ";
    private static final String MAX_RATE_MESSAGE = "The highest Bitcoin rate in the last 30 days was ";
    private static final String EXIT_OR_CURRENCY_SELECT_MESSAGE = "Type \"exit\" or select new the currency";

    private static final Logger log = LoggerFactory.getLogger(RunnerService.class);

    @Autowired
    IOCommandLine ioCommandLine;

    @Autowired
    ValidationService validationService;

    @Autowired
    CurrencyPriceService currencyPriceService;

    @Autowired
    HistoricalPriceService historicalPriceService;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            log.info("Application started");
            ioCommandLine.writeOutput(WELCOME_MESSAGE);
            ioCommandLine.writeOutput(CURRENCY_SELECT_MESSAGE);
            String input = ioCommandLine.readInput().toUpperCase();
            while (!input.equals(EXIT_PHRASE)) {
                try {
                    validationService.validate(input);
                    ioCommandLine.writeOutput(CURRENT_RATE_MESSAGE + currencyPriceService.getCurrentPrice(input));
                    ioCommandLine.writeOutput(MIN_RATE_MESSAGE + historicalPriceService.getMinHistoricalPrice(input));
                    ioCommandLine.writeOutput(MAX_RATE_MESSAGE + historicalPriceService.getMaxHistoricalPrice(input));
                } catch (IllegalArgumentException e) {
                    log.warn(e.getMessage());
                    ioCommandLine.writeOutput(e.getMessage());
                }
                ioCommandLine.writeOutput(EXIT_OR_CURRENCY_SELECT_MESSAGE);
                input = ioCommandLine.readInput().toUpperCase();
            }
            log.info("Application ended");
        };
    }
}
