package com.entreprises.management.employee_service.dtos;

public record SalaryAdvanceRequest(
        Double amount,
        String reason
) {}