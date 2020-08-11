package com.stfluffy.currencyconverter.exception;

public class CentralBankExchRateServiceException extends RuntimeException {
    public CentralBankExchRateServiceException() {
        super();
    }

    public CentralBankExchRateServiceException(String message) {
        super(message);
    }
}
