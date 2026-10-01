package com.rk.experiments.one_to_one_join_table.entity;

import jakarta.persistence.*;

@Entity(name = "patients_join")
@Table(name = "patients_join")
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

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinTable(
            name = "patients_med_history_join_table",
            joinColumns = @JoinColumn(name = "pat_id"),
            inverseJoinColumns = @JoinColumn(name = "med_id")
    )
    private MedicalHistory medicalHistory;

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
