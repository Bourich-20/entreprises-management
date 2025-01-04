package com.entreprises.management.employee_service.dtos;

import java.util.Date;

public record PaySlipResponse(
        Long paySlipId,
        Double basicSalary,
        Double bonuses,
        Double deductions,
        Double netSalary,
        Date payDate
) {}
