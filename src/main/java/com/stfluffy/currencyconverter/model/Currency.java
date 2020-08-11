package com.stfluffy.currencyconverter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "currency")
@Getter
@Setter
public class Currency {

    /**
     * Identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Digital currency code.
     */
    @Column(nullable = false)
    private String numCode;

    /**
     * Alphabetic currency code.
     */
    @Column(nullable = false)
    private String charCode;

    /**
     * Currency denomination.
     */
    @Column(nullable = false)
    private int denomination;

    /**
     * Currency name.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Currency amount.
     */
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal value;

}
