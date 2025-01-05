package com.entreprises.management.employee_service.repository;

import com.entreprises.management.employee_service.entities.CnssPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CnssPaymentRepository extends JpaRepository<CnssPayment, Long> {
    List<CnssPayment> findHistoryByEmployeeId(Long employeeId);
}