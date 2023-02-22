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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AuditServiceImp implements AuditService {

    @Autowired
    AuditRepository auditRepository;

    @Override
    public void createAudit(Audit audit) {
        auditRepository.save(audit);
    }

    @Override
    public void updateAudit(Audit audit) {
        Optional<Audit> auditObj = auditRepository.findById(audit.getRequestId());
        if (auditObj.isPresent()) {
            auditRepository.save(audit);
        } else {
            throw new RuntimeException("Unable to locate Audit with ID: " + audit.getRequestId());
        }

    }

    @Override
    public List<Audit> getAllAudit() {
        return auditRepository.findAll();
    }

    @Override
    public void deleteAudit(Long auditId) {
        Optional<Audit> auditObj = auditRepository.findById(auditId);
        if (auditObj.isPresent()) {
            auditRepository.deleteById(auditId);
        } else {
            throw new RuntimeException("Unable to locate Audit with ID: " + auditId);
        }
    }
}
