package com.stfluffy.currencyconverter.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CentralBankExchRateDto {

    private Long id;
    private LocalDate localDate;
    private List<CurrencyDto> currencies;

}
