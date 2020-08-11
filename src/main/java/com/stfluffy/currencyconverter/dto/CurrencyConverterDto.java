package com.stfluffy.currencyconverter.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConverterDto {

    private CurrencyDto baseCurrency;
    private CurrencyDto conversionCurrency;
    private BigDecimal amount;
    private BigDecimal exchangeResultAmount;

}
