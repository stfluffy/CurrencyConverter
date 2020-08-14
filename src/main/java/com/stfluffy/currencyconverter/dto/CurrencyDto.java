package com.stfluffy.currencyconverter.dto;

import lombok.*;

import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "(?<=^| )\\d+(\\.\\d+)?(?=$| )")
    private BigDecimal value;

}
