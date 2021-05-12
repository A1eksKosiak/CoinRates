package com.kuehnenagel.coinrates.service;

import com.kuehnenagel.coinrates.dto.BPIHistoricalPriceDTO;
import com.kuehnenagel.coinrates.dto.HistoricalPriceDTO;
import com.kuehnenagel.coinrates.utils.JsonUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HistoricalPriceServiceTest {
    private static final String VALID_CURRENCY = "EUR";
    private static final String ERROR_MESSAGE = "Something went wrong";

    @InjectMocks
    private HistoricalPriceService historicalPriceService;

    @Mock
    private JsonUtil jsonUtil;

    private HistoricalPriceDTO historicalPriceDTO;
    private BPIHistoricalPriceDTO bpiHistoricalPriceDTO;
    private Map<String, Double> dailyRates;

    @Before
    public void init() {
        historicalPriceDTO = new HistoricalPriceDTO();
        bpiHistoricalPriceDTO = new BPIHistoricalPriceDTO();
        dailyRates = new HashMap<>();
        dailyRates.put("Min", 1.0);
        dailyRates.put("Average", 2.0);
        dailyRates.put("Max", 3.0);
        bpiHistoricalPriceDTO.setDailyRates(dailyRates);
        historicalPriceDTO.setBpi(bpiHistoricalPriceDTO);
    }

    @After
    public void tearDown() {
        historicalPriceDTO = null;
        bpiHistoricalPriceDTO = null;
        dailyRates.clear();
    }

    @Test
    public void getMinHistoricalPrice_shouldReturnMin_whenValidCurrencyProvided() throws IOException {
        when(jsonUtil.getHistoricalPrice(anyString())).thenReturn(historicalPriceDTO);

        String actual = historicalPriceService.getMinHistoricalPrice(VALID_CURRENCY);
        assertEquals("1.0", actual);
    }

    @Test
    public void getMinHistoricalPrice_shouldReturnErrorMessage_whenMapIsEmpty() throws IOException {
        dailyRates.clear();
        when(jsonUtil.getHistoricalPrice(anyString())).thenReturn(historicalPriceDTO);

        String actual = historicalPriceService.getMinHistoricalPrice(VALID_CURRENCY);
        assertEquals(ERROR_MESSAGE, actual);
    }

    @Test
    public void getMaxHistoricalPrice_shouldReturnMax_whenValidCurrencyProvided() throws IOException {
        when(jsonUtil.getHistoricalPrice(anyString())).thenReturn(historicalPriceDTO);

        String actual = historicalPriceService.getMaxHistoricalPrice(VALID_CURRENCY);
        assertEquals("3.0", actual);
    }

    @Test
    public void getMaxHistoricalPrice_shouldReturnErrorMessage_whenMapIsEmpty() throws IOException {
        dailyRates.clear();
        when(jsonUtil.getHistoricalPrice(anyString())).thenReturn(historicalPriceDTO);

        String actual = historicalPriceService.getMaxHistoricalPrice(VALID_CURRENCY);
        assertEquals(ERROR_MESSAGE, actual);
    }
}