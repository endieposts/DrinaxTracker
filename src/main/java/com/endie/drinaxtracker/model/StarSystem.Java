package com.endie.drinaxtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "starsystem")
public class StarSystem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable=false, updatable=false)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String notes;

    @NotBlank(message = "Sector is mandatory")
    private String sector;

    @NotBlank(message = "Subsector is mandatory")
    private String subSector;

    @ColumnDefault("0")
    private String drinaxAttitude;

    @ColumnDefault("0")
    private String partyAttitude;

}