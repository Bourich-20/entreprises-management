package com.entreprises.management.employee_service.dtos;

import java.util.Date;

public record CreditResponse(
        Long creditId,
        Double amount,
        String description,
        Date date
) {}