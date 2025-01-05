package com.entreprises_management.project_service.dtos;

import java.util.Date;

public record PhaseDTO(
        Long id,
        String phaseName,
        Date startDate,
        Date endDate,
        String responsible
) {
}
