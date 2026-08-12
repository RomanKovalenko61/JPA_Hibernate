package com.rk.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPQLEx2 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                // *****************************************************************************************************
                // All Students with 'a' in name
                // '%a%' '%А%' результат одинаковый (почему?)
//                List<Student> students = entityManager.createQuery("select s from Student s where s.name like '%a%'").getResultList();
//                System.out.println(students);


                // *****************************************************************************************************
                // All Students without avg grade info
//                List<Student> students = entityManager.createQuery("select s from Student s where s.avgGrade is null ").getResultList();
//                System.out.println(students);


                // *****************************************************************************************************
                // All Students with 'l' in name and avg grade > 8.5
//                List<Student> students = entityManager
//                        .createQuery("select s from Student s where s.name like '%l%' and s.avgGrade > 8.5 ")
//                        .getResultList();
//                System.out.println(students);


                // *****************************************************************************************************
                // All Students names
//                List<String> names = entityManager
//                        .createQuery("select s.name from Student s")
//                        .getResultList();
//                System.out.println(names);

                // *****************************************************************************************************
                // All Students names and avg grades
//                List<Object[]> studentsInfo = entityManager
//                        .createQuery("select s.name, s.avgGrade from Student s")
//                        .getResultList();
//                studentsInfo.forEach(o -> System.out.println(Arrays.toString(o)));


                // *****************************************************************************************************
                // Max avg grade
//                Double max = (Double) entityManager
//                        .createQuery("select max(s.avgGrade) from Student s")
//                        .getSingleResult();
//                System.out.println(max);


                // *****************************************************************************************************
                // Average of avg grade
                Double avg = (Double) entityManager
                        .createQuery("select avg(s.avgGrade) from Student s")
                        .getSingleResult();
                System.out.printf("%.2f", avg);

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
