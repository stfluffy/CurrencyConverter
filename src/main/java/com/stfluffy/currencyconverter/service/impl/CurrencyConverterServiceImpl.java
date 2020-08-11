package com.stfluffy.currencyconverter.service.impl;

import com.stfluffy.currencyconverter.dto.CurrencyDto;
import com.stfluffy.currencyconverter.service.CurrencyConverterService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private final MathContext MATH_CONTEXT = MathContext.DECIMAL64;

    public BigDecimal convertCurrenciesToRuble(BigDecimal amount, CurrencyDto currency) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArithmeticException("The amount can't be negative!");
        }
        BigDecimal currencyPerUnit = currency.getValue()
                .divide(BigDecimal.valueOf(currency.getDenomination()), MATH_CONTEXT);

        return currencyPerUnit.multiply(amount);
    }

    public BigDecimal convertRubleToCurrencies(BigDecimal amount, CurrencyDto currency) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArithmeticException("The amount can't be negative!");
        }
        BigDecimal currencyPerUnit = currency.getValue()
                .divide(BigDecimal.valueOf(currency.getDenomination()), MATH_CONTEXT);

        BigDecimal unitRubleInCurrency = BigDecimal.ONE.divide(currencyPerUnit, MATH_CONTEXT);

        return unitRubleInCurrency.multiply(amount);
    }

    public BigDecimal convertCurrencies(BigDecimal amount, CurrencyDto baseCurrency,
                                        CurrencyDto conversionCurrency) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArithmeticException("The amount can't be negative!");
        }

        BigDecimal convertToBaseCurrency = convertCurrenciesToRuble(amount, baseCurrency);

        return convertRubleToCurrencies(convertToBaseCurrency, conversionCurrency);
    }
}
