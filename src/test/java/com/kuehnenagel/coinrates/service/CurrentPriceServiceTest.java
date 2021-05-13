package com.kuehnenagel.coinrates.service;

import com.kuehnenagel.coinrates.dto.CurrencyDTO;
import com.kuehnenagel.coinrates.utils.JsonUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrentPriceServiceTest {
    private static final String VALID_CURRENCY = "EUR";
    private static final String ERROR_MESSAGE = "Something went wrong";
    private static final String ONE_DOT_ZERO = "1.0";

    private Optional<CurrencyDTO> optionalCurrencyDTO;

    @InjectMocks
    private CurrencyPriceService currencyPriceService;

    @Mock
    private JsonUtil jsonUtil;

    @Before
    public void init() {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCode(VALID_CURRENCY);
        currencyDTO.setRateFloat(1.0);
        optionalCurrencyDTO = Optional.of(currencyDTO);
    }

    @After
    public void tearDown() {
        optionalCurrencyDTO = Optional.empty();
    }

    @Test
    public void getCurrentPrice_shouldReturnErrorMessage_whenCurrencyDTOIsEmpty() {
        optionalCurrencyDTO = Optional.empty();
        when(jsonUtil.getCurrentPrice(anyString())).thenReturn(optionalCurrencyDTO);

        String actual = currencyPriceService.getCurrentPrice(VALID_CURRENCY);
        assertEquals(ERROR_MESSAGE, actual);
    }

    @Test
    public void getCurrentPrice_shouldReturnRate_whenCurrencyDTOIsValid() {
        when(jsonUtil.getCurrentPrice(anyString())).thenReturn(optionalCurrencyDTO);

        String actual = currencyPriceService.getCurrentPrice(VALID_CURRENCY);
        assertTrue(optionalCurrencyDTO.isPresent());
        assertEquals(ONE_DOT_ZERO, actual);
    }

}
