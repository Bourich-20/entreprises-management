package com.entreprises.management.division.dtos;

import java.io.Serializable;

import java.util.List;

public record ServiceResponse(
        Long id,
        String name,
        Long divisionId,
        Long chiefId,
        List<Long> employeeIds
) {}