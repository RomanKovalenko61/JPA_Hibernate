package com.rk.persistence_context;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EntityStatesEx3 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                Teacher teacher1 = new Teacher("Rio", "Berger", "Biology", false);
                Teacher teacher2 = new Teacher("Karina", "Dennis", "Economy", false);

                entityManager.persist(teacher1);
                entityManager.persist(teacher2);

                transaction.commit();
            } catch (Exception ex) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }
}
