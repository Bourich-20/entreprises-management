package com.entreprises.management.employee_service.dtos;

public record CnssPaymentRequest(
        Double amount,
        String paymentMethod
) {}
