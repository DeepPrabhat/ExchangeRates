package com.currency.currencyexchange.repository;

import com.currency.currencyexchange.model.Audit_INFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Audit_INFO,Long> {
}
