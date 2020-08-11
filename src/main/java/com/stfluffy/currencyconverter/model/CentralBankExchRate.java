package com.stfluffy.currencyconverter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "central_bank_exch_rate")
@Getter
@Setter
public class CentralBankExchRate {

    /**
     * Identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Date of receipt of quotes.
     */
    @Column(nullable = false, unique = true)
    private LocalDate localDate;

    /**
     * List of currencies.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Currency> currencies;
}
