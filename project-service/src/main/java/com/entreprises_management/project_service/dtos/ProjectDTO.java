package com.entreprises_management.project_service.dtos;
import com.entreprises_management.project_service.entitie.ProjectStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public record ProjectDTO(
        Long id,
        String nomProjet,
        Double budget,
        Double montantPaye,
        String description,
        String type,
        ProjectStatus etat,
        String proprietaire,
        Date dateCreation,
        List<EmployeeDTO> employees,
        List<PhaseDTO> phases,
        List<ExpenseDTO> expenses,
        InvoiceDTO invoice,
        DeliveryNoteDTO deliveryNote
)  implements Serializable {
}