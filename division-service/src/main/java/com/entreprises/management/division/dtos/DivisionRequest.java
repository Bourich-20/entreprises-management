package com.entreprises.management.division.dtos;

import java.io.Serializable;

public record DivisionRequest(
        String name
) implements Serializable { }
