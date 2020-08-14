package com.stfluffy.currencyconverter.controller;

import com.stfluffy.currencyconverter.dto.CentralBankExchRateDto;
import com.stfluffy.currencyconverter.dto.CurrencyConverterDto;
import com.stfluffy.currencyconverter.service.CentralBankExchRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CurrencyExchangeController {

    private final CentralBankExchRateService service;

    @GetMapping("/converter")
    public String converterPage(Model model) throws Exception {
        CentralBankExchRateDto getCourse = service.getLatestExchangeRate();

        model.addAttribute("currencies", getCourse.getCurrencies());

        return "converter";
    }

    @PostMapping("/converter") //TODO: Сделать валидацию.
    public String test(@RequestParam(value = "baseCurrency") long idBaseCurrency,
                       @RequestParam(value = "conversionCurrency") long idConversionCurrency,
                       @RequestParam(value = "amount") String amount,
                       Model model) throws Exception {
        CentralBankExchRateDto getCourse = service.getLatestExchangeRate();
        CurrencyConverterDto currencyConverter = service.exchangeCurrency(idBaseCurrency,
                                                                        idConversionCurrency,
                                                                        amount);

        model.addAttribute("currencies",  getCourse.getCurrencies());
        model.addAttribute("selectedBaseCurrency", currencyConverter.getBaseCurrency());
        model.addAttribute("selectedConversionCurrency", currencyConverter.getConversionCurrency());
        model.addAttribute("exchangeResultAmount", currencyConverter.getExchangeResultAmount());
        model.addAttribute("charCodeResult", currencyConverter.getConversionCurrency().getCharCode());

        return "converter";
    }
}

