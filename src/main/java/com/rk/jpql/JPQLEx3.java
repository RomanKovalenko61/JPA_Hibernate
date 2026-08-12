package com.rk.jpql;

import jakarta.persistence.*;

public class JPQLEx3 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                // *****************************************************************************************************
                // Position params
                // All Students with 'l' in name and avg grade > 8.5
//                Query query = entityManager.createQuery("select s from Student s "
//                        + " where s.name like ?1 and s.avgGrade > ?2 ");
//                query.setParameter(1, "%l%");
//                query.setParameter(2, 8.5);
//                List<Student> students = query.getResultList();
//                System.out.println(students);


                // *****************************************************************************************************
                // Named params
                // All Students with 'l' in name and avg grade > 8.5
//                Query query = entityManager.createQuery("select s from Student s "
//                        + " where s.name like :letter and s.avgGrade > :grade ");
//                query.setParameter("letter", "%l%");
//                query.setParameter("grade", 8.5);
//                List<Student> students = query.getResultList();
//                System.out.println(students);


                // *****************************************************************************************************
                // Update
//                Query query = entityManager.createQuery("update Student s set s.avgGrade = 7.0" +
//                        " where length(s.surname) > 6 ");
//                query.executeUpdate();


                // *****************************************************************************************************
                // Delete
                Query query = entityManager.createQuery("DELETE Student s WHERE s.avgGrade < 7.5 OR s.avgGrade is NULL ");
                query.executeUpdate();

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
