package com.rk.persistence_context.jpa_methods;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class RefreshEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                Teacher teacher = entityManager.find(Teacher.class, 1);
                System.out.println(entityManager.contains(teacher));

                teacher.setSubject("CS");
                teacher.setProfessor(false);

                // игнориуем сеттеры т.к. в контекст заново считали сущность
                entityManager.refresh(teacher);
                System.out.println(entityManager.contains(teacher));

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
