package com.entreprises.management.division.entitie;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Nom du service

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division; // Division à laquelle le service appartient

    @Column(name = "chief_id", nullable = false)
    private Long chiefId;

    @ElementCollection
    @CollectionTable(name = "service_employees", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "employee_id")
    private List<Long> employeeIds;
}