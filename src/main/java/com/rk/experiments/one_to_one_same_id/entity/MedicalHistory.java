package com.rk.experiments.one_to_one_same_id.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "med_history")
public class MedicalHistory {

    @Id
    private Integer id;

    private String info;

    public MedicalHistory() {
    }

    public MedicalHistory(String info) {
        this.info = info;
    }

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
