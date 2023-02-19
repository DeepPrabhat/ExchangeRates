package com.currency.currencyexchange.controller;

import com.currency.currencyexchange.model.Audit_INFO;
import com.currency.currencyexchange.services.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController {

    @Autowired
    ExchangeRate exchangeRate;

    @GetMapping("/home")
    public String check()
    {
        return "Working";
    }

    @GetMapping("/exchange/{to}-{from}-{amt}")
    public Audit_INFO getExchangeRates(@PathVariable String to,@PathVariable String from,@PathVariable Long amt)
    {
        return exchangeRate.CreateExchangeRates(to,from,amt);
    }
}
