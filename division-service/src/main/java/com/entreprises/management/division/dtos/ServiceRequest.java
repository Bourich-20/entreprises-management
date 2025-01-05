package com.entreprises.management.division.dtos;

import java.util.List;

public record ServiceRequest(
        String name,
        Long divisionId,
        Long chiefId,
        List<Long> employeeIds
) {}