package com.stfluffy.currencyconverter.exception;

public class ParseCentralBankApiException extends RuntimeException {

    public ParseCentralBankApiException() {
        super();
    }

    public ParseCentralBankApiException(String message) {
        super(message);
    }
}
