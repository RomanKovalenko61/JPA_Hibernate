package com.rk.jpql;

import com.rk.jpql.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class NamedQueryEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            try {

//                Query query = entityManager.createNamedQuery("University.allUniversitiesLessOrEqualTo2");
//                List<University> list = query.getResultList();
//                System.out.println(list);

//                Query query = entityManager.createNamedQuery("University.studentsWithAvgGradeBetween");
//                query.setParameter("from", 6);
//                query.setParameter("to", 8);
//                List<Student> list = query.getResultList();
//                list.forEach(System.out::println);

                TypedQuery<Student> query = entityManager.createNamedQuery("University.studentsWithAvgGradeBetween", Student.class);
                query.setParameter("from", 6);
                query.setParameter("to", 8);
                List<Student> list = query.getResultList();
                list.forEach(System.out::println);

            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }
}
