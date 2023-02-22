package com.assessment.exchangeapi.service;

import com.assessment.exchangeapi.model.Audit;
import com.assessment.exchangeapi.repository.AuditRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
@Transactional
public class ExchangeRateServiceImp implements ExchangeRateService {

    @Autowired
    AuditService auditService;
    @Autowired
    AuditRepository auditRepository;

    @Override
    public String getData(String to,String from) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.apilayer.com/exchangerates_data/convert?to="+to+"&from="+from+"&amount=1"))
                .header("apiKey", "N73LxvJajI28Hyr8AcoaIN8zherMdF5N")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


    @Override
    public Map<String, Double> fetchExchangeRates(String date, List<String> currencies)
            throws IOException, InterruptedException {
        Map<String, Double> exchangeRates = new HashMap<>();

        for(String currency : currencies) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.apilayer.com/exchangerates_data/"+date+"?symbols="+ currency +"&base=USD"))
                    .header("apiKey", "N73LxvJajI28Hyr8AcoaIN8zherMdF5N")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode jsonNode = new ObjectMapper().readTree(response.body());
            Double rate = jsonNode.get("rates").get(currency).asDouble();
            exchangeRates.put(currency, rate);
        }

        Audit audit = new Audit();
        audit.setRequest("https://api.apilayer.com/exchangerates_data/"+date+"?symbols="+ currencies +"&base=USD");
        audit.setResponse(exchangeRates.toString());
        audit.setStatus(Audit.Status.REQUEST_SENT);
        auditService.createAudit(audit);
        if (!auditRepository.findById(audit.getRequestId()).isPresent()) {
        } else {
            auditService.updateAudit(audit);
        }
        return exchangeRates;
    }
}

