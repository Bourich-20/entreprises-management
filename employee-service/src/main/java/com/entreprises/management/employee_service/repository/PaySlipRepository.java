package com.entreprises.management.employee_service.repository;

import com.entreprises.management.employee_service.entities.PaySlip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaySlipRepository extends JpaRepository<PaySlip, Long> {
    List<PaySlip> findByEmployeeId(Long employeeId);
}