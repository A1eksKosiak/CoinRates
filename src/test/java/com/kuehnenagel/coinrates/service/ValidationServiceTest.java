package com.kuehnenagel.coinrates.service;

import com.kuehnenagel.coinrates.dto.SupportedCurrencyDTO;
import com.kuehnenagel.coinrates.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {
    private static final String ERROR_MESSAGE_FOR_NULL = "Input should not be null";
    private static final String ERROR_MESSAGE_UNSUPPORTED_CURRENCY = "The provided currency is not supported";
    private static final String INVALID_CURRENCY = "Invalid currency";
    private static final String VALID_CURRENCY = "EUR";


    @InjectMocks
    private ValidationService validationService;

    @Mock
    private JsonUtil jsonUtil;

    @Test
    public void validate_shouldThrowIllegalArgumentExceptionMessageForNull_whenCurrencyIsNull() throws IOException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> validationService.validate(null));
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(ERROR_MESSAGE_FOR_NULL));
    }

    @Test
    public void validate_shouldThrowIllegalArgumentExceptionMessageForUnsupportedCurrency_whenUnsupportedCurrency() throws IOException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> validationService.validate(INVALID_CURRENCY));
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(ERROR_MESSAGE_UNSUPPORTED_CURRENCY));
    }

    @Test
    public void validate_shouldThrowIllegalArgumentExceptionMessageForUnsupportedCurrency_whenSupportedCurrenciesListIsEmpty() throws IOException {
        List<SupportedCurrencyDTO> supportedCurrencyDTOList = new ArrayList<>();
        when(jsonUtil.getSupportedCurrencies()).thenReturn(supportedCurrencyDTOList);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> validationService.validate(VALID_CURRENCY));
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(ERROR_MESSAGE_UNSUPPORTED_CURRENCY));
    }
}