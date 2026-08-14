package com.rk.relationship.one_to_many;

import com.rk.relationship.one_to_many.entity.Student;
import jakarta.persistence.*;

import java.util.List;

public class NPlusOneProblem {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

//                University university1 = new University("Harvard", Date.valueOf("1636-10-28"));
//                University university2 = new University("MIT", Date.valueOf("1861-04-10"));
//                University university3 = new University("Stanford", Date.valueOf("1891-10-01"));
//
//                Student student1 = new Student("Chanel", "King", 9.1);
//                Student student2 = new Student("Leo", "Farrell", 8.4);
//                Student student3 = new Student("Julia", "Dean", 8.7);
//
//                university1.addStudentToUniversity(student1);
//                university2.addStudentToUniversity(student2);
//                university3.addStudentToUniversity(student3);
//
//                entityManager.persist(university1);
//                entityManager.persist(university2);
//                entityManager.persist(university3);

                // N+1 problem
                Query query = entityManager.createQuery("SELECT s FROM Student s JOIN FETCH s.university");
                List<Student> students = query.getResultList();

                for (Student s : students) {
                    System.out.println(s.getName() + " " + s.getUniversity().getName());
                }

                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }
}
