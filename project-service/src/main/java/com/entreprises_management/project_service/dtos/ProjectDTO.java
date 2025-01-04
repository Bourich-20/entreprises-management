package com.entreprises_management.project_service.dtos;
import com.entreprises_management.project_service.entitie.ProjectStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public record ProjectDTO(
        Long id,
        String name,
        Double budget,
        String owner,
        String description,
        ProjectStatus status,
        Date createdAt,
        List<Long> employeeIds
)  implements Serializable {
}