package com.rk.persistence_context;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EntityStatesEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                // Transient (new)
                Teacher teacher = new Teacher("Alessandro", "Lozano", "CS", true);
                System.out.println(entityManager.contains(teacher));

                // Persistent (Managed)
                entityManager.persist(teacher);
                System.out.println(entityManager.contains(teacher));

                transaction.commit();
                // Detached после закрытия entityManager
                System.out.println(entityManager.contains(teacher));
            } catch (Exception ex) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }
}
