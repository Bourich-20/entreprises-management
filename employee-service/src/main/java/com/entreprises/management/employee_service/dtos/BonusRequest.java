package com.entreprises.management.employee_service.dtos;

public record BonusRequest(
        Double bonusAmount,
        String reason
) {}