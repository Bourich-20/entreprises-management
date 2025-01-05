package com.entreprises_management.project_service.entitie;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project; // Projet auquel le bon de livraison est lié
}
