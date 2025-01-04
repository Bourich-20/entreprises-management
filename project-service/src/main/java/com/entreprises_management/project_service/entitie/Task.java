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

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @Column(name = "responsible_employee_id")
    private Long responsibleEmployeeId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
