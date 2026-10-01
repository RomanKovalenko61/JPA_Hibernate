package com.rk.experiments.one_to_one_same_id.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public Patient() {
    }

    public Patient(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "patient", cascade = CascadeType.PERSIST, optional = false)
    private MedicalHistory medicalHistory;

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
