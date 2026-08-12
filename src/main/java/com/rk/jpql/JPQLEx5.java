package com.rk.jpql;

import com.rk.jpql.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPQLEx5 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                System.out.println("FIRST Select");
                Student student1 = entityManager.find(Student.class, 3);
                Student student2 = entityManager.find(Student.class, 3);

                System.out.println("SECOND Select");
                // JPA не знает что написано в строке и не проверяет persistence context
                Student student3 = (Student) entityManager.createQuery("SELECT s FROM Student s WHERE s.id = 3")
                        .getSingleResult();

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

