package com.entreprises.management.employee_service.repository;

import com.entreprises.management.employee_service.entities.SalaryAdvance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryAdvanceRepository extends JpaRepository<SalaryAdvance, Long> {
    List<SalaryAdvance> findByEmployeeId(Long employeeId);
}