package com.stfluffy.currencyconverter.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {

    private Long id;
    private String numCode;
    private String charCode;
    private int denomination;
    private String name;
    private BigDecimal value;

}
