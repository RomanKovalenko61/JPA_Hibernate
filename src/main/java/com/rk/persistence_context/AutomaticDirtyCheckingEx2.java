package com.rk.persistence_context;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AutomaticDirtyCheckingEx2 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                // изменения будут применены, несмотря на две транзакции т.к. persistence context один и тот же
                transaction.begin();
                Teacher teacher = entityManager.find(Teacher.class, 4);
                transaction.commit();

                // для изменений нужно чтоб где-либо был commit
                teacher.setSubject("CS");

                transaction.begin();
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
