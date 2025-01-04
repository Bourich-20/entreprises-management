package com.entreprises.management.employee_service.dtos;

public record PaySlipRequest(
        Double basicSalary,
        Double bonuses,
        Double deductions
) {}
