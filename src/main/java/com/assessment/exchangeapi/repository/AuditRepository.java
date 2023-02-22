package com.assessment.exchangeapi.repository;

import com.assessment.exchangeapi.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit,Long> {
}
