package com.stfluffy.currencyconverter.service;

import com.stfluffy.currencyconverter.dto.CentralBankExchRateDto;
import com.stfluffy.currencyconverter.dto.CurrencyConverterDto;

public interface CentralBankExchRateService {

    /**
     * Получет последние курсы валют на данный день
     * @return CentralBankExchRateDto
     */
    CentralBankExchRateDto getLatestExchangeRate() throws Exception;

    /**
     * Конвертирует валюты
     *
     * @param idBaseCurrency id базовой валюты
     * @param idConversionCurrency id валюты конвертвции
     * @param amount сумма к конвертации
     * @return CurrencyConverterDto
     */
    CurrencyConverterDto exchangeCurrency(long idBaseCurrency, long idConversionCurrency, String amount);

}
