package com.entreprises.management.employee_service.repository;

import com.entreprises.management.employee_service.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDivisionId(Long divisionId);
    List<Employee> findByProjectId(Long projectId);
}