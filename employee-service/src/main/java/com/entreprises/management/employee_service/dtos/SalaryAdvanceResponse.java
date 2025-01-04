package com.entreprises.management.employee_service.dtos;

import java.util.Date;

public record SalaryAdvanceResponse(
        Long advanceId,
        Double amount,
        String reason,
        Date date
) {}