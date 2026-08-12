package com.rk.jpql;

import com.rk.jpql.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class NativeQueryEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            try {

                // Работаем со столбцами БД, а не классами!!!

//                Query query = entityManager.createNativeQuery("SELECT * FROM students", Student.class);
//                List<Student> students = query.getResultList();
//                System.out.println(students);
                //******************************************************************************************************


                // JPA не поддерживает named params но на этой версии работает
                Query query = entityManager.createNativeQuery("SELECT * FROM students WHERE avg_grade > ?1 AND "
                        + " LENGTH(name) = ?2", Student.class);
                query.setParameter(1, 8);
                query.setParameter(2, 5);
                List<Student> students = query.getResultList();
                System.out.println(students);

            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }
}
