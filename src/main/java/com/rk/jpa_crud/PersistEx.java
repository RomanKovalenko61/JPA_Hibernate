package com.rk.jpa_crud;

import com.rk.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PersistEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
//            Student student = new Student(null, "Scott", 7.4);
            Student student = new Student("Mathew", "Perry", 8.9);

            try {
                transaction.begin();
                entityManager.persist(student);
                transaction.commit(); // увидим id даже если не коммитим, но в БД не будет
            } catch (Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }

            System.out.println(student);
        }
    }
}
