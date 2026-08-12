package com.rk.jpql;

import com.rk.jpql.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JPQLEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                // ALL Students
                // select * from students;
                // JPQL работаем с классом и его полями, а не таблицами БД
//                Query query = entityManager.createQuery("select s from Student s");
//                List<Student> students = query.getResultList();
//                List<Student> students = entityManager.createQuery("select s from Student s").getResultList();

                // TypedQuery<Student> (interface impl query)
//                List<Student> students = entityManager.createQuery("select s from Student s", Student.class).getResultList();


                // *****************************************************************************************************
                // HQL
//                List<Student> students = entityManager.createQuery("from Student s").getResultList();
//                System.out.println(students);


                // *****************************************************************************************************
                // All Students with name Leo
//                List<Student> students = entityManager.createQuery("select s from Student s where s.name = 'Leo' ").getResultList();
//                System.out.println(students);


                // *****************************************************************************************************
                // All Students with avg grade > 8.5
//                List<Student> students = entityManager.createQuery("select s from Student s where s.avgGrade > 8.5").getResultList();
//                System.out.println(students);


                // *****************************************************************************************************
                // All Students with avg grade between 7 and 8
                List<Student> students = entityManager.createQuery("select s from Student s where s.avgGrade between 7 and 8").getResultList();
                System.out.println(students);

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
