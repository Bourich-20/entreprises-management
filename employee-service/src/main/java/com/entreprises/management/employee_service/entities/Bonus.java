package com.entreprises.management.employee_service.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private Double bonusAmount;
    private String reason;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
