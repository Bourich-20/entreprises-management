package com.entreprises.management.employee_service.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String cnss;
    private String rib;
    private String cin;
    private Double salaire;

    @Enumerated(EnumType.STRING)
    private ContratType typeContrat; // CDI, CDD

    @Enumerated(EnumType.STRING)
    private EmployeeStatus statut; // EncoreEmploye, Retraite, Quitte, Decede
}