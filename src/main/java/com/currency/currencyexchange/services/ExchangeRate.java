package com.currency.currencyexchange.services;

import com.currency.currencyexchange.model.Audit_INFO;

import java.util.Date;

public interface ExchangeRate {

    Audit_INFO CreateExchangeRates(String to, String from, long amount);
    void getByDate(Date exchangedate);
}
