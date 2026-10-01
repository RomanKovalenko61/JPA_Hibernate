package com.rk.experiments.one_to_one_join_table.entity;

import jakarta.persistence.*;

@Entity(name = "med_history_join")
@Table(name = "med_history_join")
public class MedicalHistory {

    @Id
    @GeneratedValue
    private Integer id;

    private String info;

    public MedicalHistory() {
    }

    public MedicalHistory(String info) {
        this.info = info;
    }

    @OneToOne(mappedBy = "medicalHistory")
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
