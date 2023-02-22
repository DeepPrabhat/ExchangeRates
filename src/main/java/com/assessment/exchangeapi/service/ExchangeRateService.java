package com.assessment.exchangeapi.service;


import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExchangeRateService {
    Map<String, Double> fetchExchangeRates(String date, List<String> currency) throws IOException, InterruptedException;

    String getData(String to,String from) throws IOException, InterruptedException;
}