package com.entreprises.management.employee_service.dtos;

import java.util.Date;

public record CnssPaymentResponse(
        Long paymentId,
        Double amount,
        String paymentMethod,
        Date paymentDate
) {}
