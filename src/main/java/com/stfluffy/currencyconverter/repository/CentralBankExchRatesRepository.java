package com.stfluffy.currencyconverter.repository;

import com.stfluffy.currencyconverter.model.CentralBankExchRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CentralBankExchRatesRepository extends JpaRepository<CentralBankExchRate, Long> {

    Optional<CentralBankExchRate> findByLocalDate(LocalDate date);
}
