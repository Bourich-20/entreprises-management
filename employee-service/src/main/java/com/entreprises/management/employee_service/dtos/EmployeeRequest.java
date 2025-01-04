package com.entreprises.management.employee_service.dtos;

import com.entreprises.management.employee_service.entities.ContratType;
import com.entreprises.management.employee_service.entities.EmployeeStatus;

import java.io.Serializable;

public record EmployeeRequest(
        String nom,
        String prenom,
        String email,
        String cnss,
        String rib,
        String cin,
        Double salaire,
        ContratType typeContrat,
        EmployeeStatus statut
) implements Serializable { }
