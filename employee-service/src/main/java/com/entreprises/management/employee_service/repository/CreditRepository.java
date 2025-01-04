package com.entreprises.management.employee_service.repository;

import com.entreprises.management.employee_service.dtos.CreditResponse;
import com.entreprises.management.employee_service.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<CreditResponse> findByEmployeeId(Long employeeId);
}