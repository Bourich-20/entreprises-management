package com.entreprises.management.employee_service.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    private String telephone;
    private String adresseEmail;
    private String genre;
    private String nomBanque;
    private Date dateNaissance;
    private String profilEntreprise;
    private String cv;
    private Date dateEmploi;

    // Relations
    @OneToMany(mappedBy = "employee")
    private List<Bonus> bonuses;

    @OneToMany(mappedBy = "employee")
    private List<CnssPayment> cnssPayments;

    @OneToMany(mappedBy = "employee")
    private List<Credit> credits;

    @OneToMany(mappedBy = "employee")
    private List<PaySlip> paySlips;

    @OneToMany(mappedBy = "employee")
    private List<SalaryAdvance> salaryAdvances;
    @Column(name = "division_id", nullable = false)
    private Long divisionId;
    @Column(name = "project_id", nullable = false)
    private Long projectId;
}
