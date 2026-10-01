package com.rk.experiments.one_to_one_same_id;

import com.rk.experiments.one_to_one_same_id.entity.MedicalHistory;
import com.rk.experiments.one_to_one_same_id.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Launcher {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                Patient patient = new Patient("John");
                MedicalHistory medicalHistory = new MedicalHistory("some info");
                patient.setMedicalHistory(medicalHistory);
                medicalHistory.setPatient(patient);

                entityManager.persist(patient);

                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}
