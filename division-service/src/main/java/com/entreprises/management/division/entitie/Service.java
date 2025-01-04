package com.entreprises.management.division.entitie;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    private Division division;
    @Column(name = "chief_id", nullable = false)
    private Long chiefId;
}