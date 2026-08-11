package com.rk.persistence_context;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TransactionEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                Teacher teacher1 = entityManager.find(Teacher.class, 3);
                Teacher teacher2 = entityManager.find(Teacher.class, 4);

                teacher1.setSubject("Geography");

                Teacher teacher3 = entityManager.find(Teacher.class, 7); // not exists
                System.out.println(teacher3.isProfessor()); // null pointer ex

                teacher2.setSubject("Geography");

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
