package com.entreprises.management.employee_service.dtos;

import java.io.Serializable;

public record DivisionResponse(
        Long id,
        String name,
        String chiefName,
        Long chiefId
) implements Serializable { }
