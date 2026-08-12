package com.rk.jpql;

import jakarta.persistence.*;

import java.util.List;

public class JPQLEx4 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                // Select universities without students
//                Query query = entityManager.createQuery("SELECT u FROM University u where u.students IS EMPTY ");
//                List<University> universities = query.getResultList();
//                universities.forEach(System.out::println);
                // ***************************************************************************************************


                // Select universities with 1 students
//                Query query = entityManager.createQuery("SELECT u FROM University u where size(u.students) = 1 ");
//                List<University> universities = query.getResultList();
//                universities.forEach(System.out::println);
                // ***************************************************************************************************


                // Sort universities by count of students
//                Query query = entityManager.createQuery("SELECT u FROM University u ORDER BY size(u.students) DESC ");
//                List<University> universities = query.getResultList();
//                universities.forEach(System.out::println);
                // ***************************************************************************************************


                // CROSS JOIN
//                Query query = entityManager.createQuery("SELECT u, s FROM University u, Student s");
//                List<Object[]> results = query.getResultList();
//
//                //Object[0] --> University
//                //Object[1] --> Student
//
//                for (Object[] result : results) {
//                    System.out.println(result[0] + " ---> " + result[1]);
//                }

                // ***************************************************************************************************


                // JOIN
//                Query query = entityManager.createQuery("SELECT u, s FROM University u JOIN u.students s");
//                List<Object[]> results = query.getResultList();
//
//                //Object[0] --> University
//                //Object[1] --> Student
//
//                for (Object[] result : results) {
//                    System.out.println(result[0] + " ---> " + result[1]);
//                }

                // ***************************************************************************************************

                // Left JOIN
                Query query = entityManager.createQuery("SELECT u, s FROM University u LEFT JOIN u.students s");
                List<Object[]> results = query.getResultList();

                //Object[0] --> University
                //Object[1] --> Student

                for (Object[] result : results) {
                    System.out.println(result[0] + " ---> " + result[1]);
                }

                // ***************************************************************************************************

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
