package com.entreprises.management.employee_service.dtos;

public record CreditRequest(
        Double amount,
        String description
) {}