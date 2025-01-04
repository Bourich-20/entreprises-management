package com.entreprises.management.employee_service.dtos;

import java.util.Date;

public record CnssPaymentHistoryResponse(
        Long paymentId,
        Double amount,
        String paymentMethod,
        Date paymentDate
) {}
