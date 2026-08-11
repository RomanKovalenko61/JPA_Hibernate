package com.rk.persistence_context;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TransactionEx2 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                Teacher teacher1 = new Teacher("Landry", "Shelton", "Economics", true);
                Teacher teacher2 = new Teacher("Vera", "Walton", "Geography", true);

                entityManager.persist(teacher1);

                Teacher teacher3 = entityManager.find(Teacher.class, 100);
                System.out.println(teacher3.getName() + " " + teacher3.getSurname()); // null pointer

                entityManager.persist(teacher2);

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    System.out.println("ROLLBACK!!!");
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
