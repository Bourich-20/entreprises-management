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

    private String name;
    private Double budget;
    private String owner;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Phase> phases;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    @ElementCollection
    @CollectionTable(name = "project_employee_ids", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "employee_id")
    private List<Long> employeeIds;

}
