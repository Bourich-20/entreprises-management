package com.entreprises.management.employee_service.repository;

import com.entreprises.management.employee_service.entities.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BonusRepository extends JpaRepository<Bonus, Long> {
    List<Bonus> findByEmployeeId(Long employeeId);
}