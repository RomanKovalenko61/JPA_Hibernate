package com.rk.persistence_context.jpa_methods;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ClearEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                Teacher teacher1 = entityManager.find(Teacher.class, 3);
                Teacher teacher2 = entityManager.find(Teacher.class, 4);
                System.out.println(entityManager.contains(teacher1)); // true
                System.out.println(entityManager.contains(teacher2)); // true

                // detach все сущностей в контексте. Дальнейшие их изменения не отразятся в БД
                entityManager.clear();
                System.out.println(entityManager.contains(teacher1)); // false
                System.out.println(entityManager.contains(teacher2)); // false

                teacher1.setProfessor(true);
                teacher2.setProfessor(true);

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
