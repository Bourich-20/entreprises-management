package com.entreprises.management.employee_service.dtos;

import java.io.Serializable;

public record EmployeeDTO(
         Long id,
         String nom,
         String prenom,
         String email,
         String cnss,
         String rib,
         String cin,
         Double salaire,
         String typeContrat,
         String statut
) implements Serializable { }
