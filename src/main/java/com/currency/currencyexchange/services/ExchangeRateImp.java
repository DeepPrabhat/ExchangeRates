package com.currency.currencyexchange.services;

import com.currency.currencyexchange.model.Audit_INFO;
import com.currency.currencyexchange.repository.AuditRepository;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class ExchangeRateImp implements ExchangeRate {

    @Autowired
    private AuditRepository auditRepository;

    @Override
    public Audit_INFO CreateExchangeRates(String to, String from, long amount) {
        String url = "https://api.apilayer.com/exchangerates_data/convert?to=" + to + "&from=" + from + "&amount=" + amount;
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "N73LxvJajI28Hyr8AcoaIN8zherMdF5N");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        Audit_INFO auditObj = new Audit_INFO();
        try {
            auditObj.setStatus(Audit_INFO.APIRequestStatus.REQUEST_SENT);
            String jsonresponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            auditObj.setStatus(Audit_INFO.APIRequestStatus.RESPONSE_READY);
            jsonresponse = goodJson(jsonresponse);
            //cannot parse nested objects and had issue with hibernate jdbc not recognising
            auditObj = new Gson().fromJson(jsonresponse, Audit_INFO.class);
            auditRepository.save(auditObj);
        } catch (Exception e) {
            System.out.println("Unable to get Rates" + e);
        }
        finally {
            return auditObj;
        }
    }

    @Override
    public void getByDate(Date exchangedate) {

    }
//I know it might be the worst way to do it / Sorry ;(
    String goodJson(String jsonresponse)
    {
        for(int i=0;i<jsonresponse.length()-1;i++)
        {
            if(jsonresponse.charAt(i)=='{' && i>0)
            {
                jsonresponse=jsonresponse.substring(0,i-10)+jsonresponse.substring(i+1);
            } else if (jsonresponse.charAt(i)=='}') {
                jsonresponse=jsonresponse.substring(0,i-1)+jsonresponse.substring(i+1);
            }
        }
        jsonresponse=jsonresponse+"}";
        return jsonresponse;
    }
}

