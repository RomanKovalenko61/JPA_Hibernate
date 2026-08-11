package com.rk.persistence_context;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EntityStatesEx2 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {
            // Persistence context появляется при создании entityManager
            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                // Persistent (Manager)
                Teacher teacher = entityManager.find(Teacher.class, 2);
                System.out.println(entityManager.contains(teacher)); // true

                // Removed - Запланирован на удаление
                entityManager.remove(teacher);
                System.out.println(entityManager.contains(teacher)); // false

                transaction.commit();
                System.out.println(entityManager.contains(teacher)); // false
            } catch (Exception ex) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }
}
