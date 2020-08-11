package com.stfluffy.currencyconverter.service;

import com.stfluffy.currencyconverter.dto.CurrencyDto;

import java.math.BigDecimal;

public interface CurrencyConverterService {

    /**
     * Метод конвертирует валюты в рубли.
     *
     * @param amount колличество валюты для перевода.
     * @param currency конвертируемая валюта.
     * @return колличество рублей после конвертации.
     */
    BigDecimal convertCurrenciesToRuble(BigDecimal amount, CurrencyDto currency);

    /**
     * Метод ковертирует рубли в другую валюту.
     *
     * @param amount колличество рублей для перевода.
     * @param currency валюта конвертации.
     * @return колличество валюты после конвертации.
     */
    BigDecimal convertRubleToCurrencies(BigDecimal amount, CurrencyDto currency);

    /**
     * Метод конвертирует из одной валюты в другую.
     *
     * @param amount количество валюты для  перевода.
     * @param baseCurrency конвертируемая валюта.
     * @param conversionCurrency валюта конвертации.
     * @return колличество валюты после конвертации.
     */
    BigDecimal convertCurrencies(BigDecimal amount, CurrencyDto baseCurrency, CurrencyDto conversionCurrency);

}
