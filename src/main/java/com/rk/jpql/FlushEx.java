package com.rk.jpql;

import com.rk.jpql.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FlushEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                Student student = entityManager.find(Student.class, 1);
                System.out.println(student);
                student.setAvgGrade(9.0);

                // перед срабатыванием query вызывается flush() и изменения попадают в БД
                Double avgGrade = (Double) entityManager.createQuery("SELECT s.avgGrade FROM Student s " +
                        " WHERE s.id = 1").getSingleResult();
                System.out.println(avgGrade);

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

