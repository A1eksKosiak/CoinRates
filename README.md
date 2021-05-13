# CoinRates
>Bitcoin rates checker test assignment for KN

Main business logic is held in RunnerService class.

User is asked to provide a currency code.

AF_Validate checks if the input is a supported currency.
```
If (false)
    show ERROR_MESSAGE_UNSUPPORTED_CURRENCY
```

AF_GetCurrencyPrice retrieves current price

AF_GetMinHistoricalPrice retrieves the lowest price for the last 30 days
```
If (price was not previously checked)
    check historical price for the last 30 days;
```
AF_GetMaxHistoricalPrice retrieves the highest price for the last 30 days
```
If (price was not previously checked)
    check historical price for the last 30 days;
```

## License
**Free Software**