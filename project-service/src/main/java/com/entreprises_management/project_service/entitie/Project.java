package com.entreprises_management.project_service.entitie;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomProjet;
    private Double budget;
    private Double montantPaye;
    private String description;
    private String type;

    @Enumerated(EnumType.STRING)
    private ProjectStatus etat; // Enum : Terminé, En Cours, Finalisé, Livré, Annulé

    private String proprietaire;
    private Date dateCreation;

    @ElementCollection
    @CollectionTable(name = "employee_project", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "employee_id")
    private List<Long> employeeIds;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Phase> phases;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @OneToOne
    @JoinColumn(name = "delivery_note_id")
    private DeliveryNote deliveryNote;

}
