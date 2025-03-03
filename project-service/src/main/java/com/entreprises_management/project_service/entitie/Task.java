package com.entreprises_management.project_service.entitie;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Date dateDebut;
    private Date dateFin;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

}
