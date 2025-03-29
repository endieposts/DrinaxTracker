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

    @NotBlank(message = "Sub-sector is mandatory")
    private String subSector;

    @ColumnDefault("0")
    private String drinaxAttitude;

    @ColumnDefault("0")
    private String partyAttitude;

    public StarSystem() {
    }

    public StarSystem(StarSystem starSystem) {
        this.id = starSystem.id;
        this.name = starSystem.name;
        this.notes = starSystem.notes;
        this.sector = starSystem.sector;
        this.subSector = starSystem.subSector;
        this.drinaxAttitude = starSystem.drinaxAttitude;
        this.partyAttitude = starSystem.partyAttitude;
    }

    public StarSystem(String name, String notes, String sector, String subSector, String drinaxAttitude, String partyAttitude) {
        this.name = name;
        this.notes = notes;
        this.sector = sector;
        this.subSector = subSector;
        this.drinaxAttitude = drinaxAttitude;
        this.partyAttitude = partyAttitude;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSubSector() {
        return subSector;
    }

    public void setSubSector(String subSector) {
        this.subSector = subSector;
    }

    public String getDrinaxAttitude() {
        return drinaxAttitude;
    }

    public void setDrinaxAttitude(String drinaxAttitude) {
        this.drinaxAttitude = drinaxAttitude;
    }

    public String getPartyAttitude() {
        return partyAttitude;
    }

    public void setPartyAttitude(String partyAttitude) {
        this.partyAttitude = partyAttitude;
    }
}