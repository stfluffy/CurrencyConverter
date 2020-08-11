package com.stfluffy.currencyconverter.service.impl;

import com.stfluffy.currencyconverter.dto.CentralBankExchRateDto;
import com.stfluffy.currencyconverter.dto.CurrencyConverterDto;
import com.stfluffy.currencyconverter.dto.CurrencyDto;
import com.stfluffy.currencyconverter.exception.CentralBankExchRateServiceException;
import com.stfluffy.currencyconverter.externalapi.centralbank.xmlparse.UnmarshalXml;
import com.stfluffy.currencyconverter.model.CentralBankExchRate;
import com.stfluffy.currencyconverter.model.Currency;
import com.stfluffy.currencyconverter.repository.CentralBankExchRatesRepository;
import com.stfluffy.currencyconverter.repository.CurrencyRepository;
import com.stfluffy.currencyconverter.service.CentralBankExchRateService;
import com.stfluffy.currencyconverter.service.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CentralBankExchRateServiceImpl implements CentralBankExchRateService {

    private final CentralBankExchRatesRepository repository;

    private final CurrencyRepository currencyRepository;

    private final CurrencyConverterService converterService;

    private final ModelMapper modelMapper;

    @Override
    public CentralBankExchRateDto getLatestExchangeRate() throws Exception {
        Optional<CentralBankExchRate> exchRateFromDb = repository.findByLocalDate(LocalDate.now());

        if (exchRateFromDb.isPresent()) {
            return convertCentralBankToDto(exchRateFromDb.get());
        }

        UnmarshalXml unmarshalXml = new UnmarshalXml();

        CentralBankExchRateDto latestExchRate = unmarshalXml.getCentralBankExchRatesDto();

        repository.save(convertCentralBankToModel(latestExchRate));

        return latestExchRate;
    }

    @Override
    public CurrencyConverterDto exchangeCurrency(long idBaseCurrency,
                                                 long idConversionCurrency,
                                                 String amount) {
        Optional<Currency> baseCurrency = currencyRepository.findById(idBaseCurrency);
        Optional<Currency> conversionCurrency = currencyRepository.findById(idConversionCurrency);

        if (baseCurrency.isPresent() && conversionCurrency.isPresent()) {
            BigDecimal exchangeResultAmount= defineConversion(new BigDecimal(amount),
                    convertCurrencyToDto(baseCurrency.get()),
                    convertCurrencyToDto(conversionCurrency.get()));

            return CurrencyConverterDto.builder()
                    .baseCurrency(convertCurrencyToDto(baseCurrency.get()))
                    .conversionCurrency(convertCurrencyToDto(conversionCurrency.get()))
                    .amount(new BigDecimal(amount))
                    .exchangeResultAmount(exchangeResultAmount)
                    .build();
        }

       throw new CentralBankExchRateServiceException("Cannot convert currency (Not found currency by id)");
    }

    /**
     * Определяет по charCode валюты, каким методом провести конвертацию
     * @param amount сумма к конвертации
     * @param baseCurrency базовая валюта
     * @param conversionCurrency конвертируемая валюта
     * @return результат конвертации
     */
    private BigDecimal defineConversion(BigDecimal amount, CurrencyDto baseCurrency,
                                                  CurrencyDto conversionCurrency) {
        if (baseCurrency.getCharCode().equals(conversionCurrency.getCharCode())) {
            return amount;
        }
        if (baseCurrency.getCharCode().equals("RUR") && !conversionCurrency.getCharCode().equals("RUR")) {
            return convertRubleToCurrencies(amount, conversionCurrency);
        }
        if (!baseCurrency.getCharCode().equals("RUR") && conversionCurrency.getCharCode().equals("RUR")) {
            return convertCurrenciesToRuble(amount, baseCurrency);
        }
        return  convertCurrencies(amount, baseCurrency, conversionCurrency);
    }


    /**
     * @see CurrencyConverterService#convertCurrenciesToRuble(BigDecimal, CurrencyDto)
     */
    private BigDecimal convertCurrenciesToRuble(BigDecimal amount, CurrencyDto currency) {
       return converterService.convertCurrenciesToRuble(amount, currency)
               .setScale(4, RoundingMode.HALF_EVEN)
               .stripTrailingZeros();
    }

    /**
     * @see CurrencyConverterService#convertRubleToCurrencies(BigDecimal, CurrencyDto)
     */
    private BigDecimal convertRubleToCurrencies(BigDecimal amount, CurrencyDto currency) {
        return converterService.convertRubleToCurrencies(amount, currency)
                .setScale(4, RoundingMode.HALF_EVEN)
                .stripTrailingZeros();
    }

    /**
     * @see CurrencyConverterService#convertCurrencies(BigDecimal, CurrencyDto, CurrencyDto)
     */
    private BigDecimal convertCurrencies(BigDecimal amount, CurrencyDto baseCurrency,
                                        CurrencyDto conversionCurrency) {
        return converterService.convertCurrencies(amount, baseCurrency, conversionCurrency)
                .setScale(4, RoundingMode.HALF_EVEN)
                .stripTrailingZeros();

    }

    /**
     * Конвертирует CentralBankExchRate (model) в CentralBankExchRate (DTO)
     * @param exchRate CentralBankExchRate.class (model)
     * @return CentralBankExchRateDto.class (DTO)
     */
    private CentralBankExchRateDto convertCentralBankToDto(CentralBankExchRate exchRate) {
        return modelMapper.map(exchRate, CentralBankExchRateDto.class);
    }

    /**
     * Конвертирует CentralBankExchRate (DTO) в CentralBankExchRate (model)
     * @param exchRateDto CentralBankExchRateDto.class (DTO)
     * @return CentralBankExchRate.class (model)
     */
    private CentralBankExchRate convertCentralBankToModel(CentralBankExchRateDto exchRateDto) {
        return modelMapper.map(exchRateDto, CentralBankExchRate.class);
    }

    /**
     * Конвертирует Currency (model) в Currency (DTO)
     * @param currency Currency.class (model)
     * @return CurrencyDto.class (DTO)
     */
    private CurrencyDto convertCurrencyToDto(Currency currency) {
        return modelMapper.map(currency, CurrencyDto.class);
    }
}
