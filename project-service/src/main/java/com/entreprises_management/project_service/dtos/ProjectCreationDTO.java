package com.entreprises_management.project_service.dtos;

import com.entreprises_management.project_service.entitie.ProjectStatus;

import java.io.Serializable;
import java.util.List;

public record ProjectCreationDTO(
        String name,
        Double budget,
        String owner,
        String description,
        ProjectStatus status,
        List<Long> employeeIds
)  implements Serializable {
}