package com.rk.jpa_crud;

import com.rk.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FindEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            Student student = null;
            try {
                student = entityManager.find(Student.class, 4L);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }

            System.out.println(student);
        }
    }
}
