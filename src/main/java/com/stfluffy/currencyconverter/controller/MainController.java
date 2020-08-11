package com.stfluffy.currencyconverter.controller;

import com.stfluffy.currencyconverter.dto.CentralBankExchRateDto;
import com.stfluffy.currencyconverter.dto.CurrencyDto;
import com.stfluffy.currencyconverter.service.CentralBankExchRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class MainController {

    private final CentralBankExchRateService service;

    @GetMapping("/")
    public String exchangeRatesPage(Model model) throws Exception {
        CentralBankExchRateDto getCourse = service.getLatestExchangeRate();
        Iterable<CurrencyDto> currencyDtos = getCourse.getCurrencies();

        model.addAttribute("currencies", currencyDtos);
        return "index";
    }
}
