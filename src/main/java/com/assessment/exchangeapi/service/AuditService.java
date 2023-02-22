package com.assessment.exchangeapi.service;

import com.assessment.exchangeapi.model.Audit;

import java.util.List;
import java.util.Map;

public interface AuditService {

    void createAudit(Audit audit);
    void updateAudit(Audit auditId);
    List<Audit> getAllAudit();
    void deleteAudit(Long auditId);
}
