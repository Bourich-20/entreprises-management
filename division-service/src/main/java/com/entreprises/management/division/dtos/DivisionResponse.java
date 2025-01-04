package com.entreprises.management.division.dtos;

import java.io.Serializable;

public record DivisionResponse(
        Long id,
        String name,
        String chiefName,
        Long chiefId
) implements Serializable { }
