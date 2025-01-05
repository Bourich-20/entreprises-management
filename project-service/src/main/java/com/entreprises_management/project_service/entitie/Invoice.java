package com.entreprises_management.project_service.entitie;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project; // Projet auquel la facture est liée
}