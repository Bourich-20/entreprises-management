package com.entreprises.management.employee_service.dtos;

import java.util.Date;

public record BonusResponse(
        Long bonusId,
        Double bonusAmount,
        String reason,
        Date date
) {}