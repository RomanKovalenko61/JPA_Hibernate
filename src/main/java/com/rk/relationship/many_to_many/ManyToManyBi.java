package com.rk.relationship.many_to_many;

import com.rk.relationship.many_to_many.entity.Teacher;
import com.rk.relationship.many_to_many.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class ManyToManyBi {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

//                // PERSIST
//
//                University university = new University("Harvard", Date.valueOf("1636-10-28"));
//                Teacher teacher1 = new Teacher("Alessandro", "Lozano", "CS", true);
//                Teacher teacher2 = new Teacher("Rio", "Berger", "Biology", false);
//                Teacher teacher3 = new Teacher("Landry", "Shelton", "Math", true);
//                university.addTeacherToUniversity(teacher1);
//                university.addTeacherToUniversity(teacher2);
//                university.addTeacherToUniversity(teacher3);
//
//                entityManager.persist(university);
//
//                Teacher teacher = new Teacher("Vera", "Walton", "Geography", true);
//                University university1 = new University("MIT", Date.valueOf("1861-04-10"));
//                University university2 = new University("Cambridge", Date.valueOf("1209-01-01"));
//                University university3 = new University("Oxford", Date.valueOf("1200-09-10"));
//                teacher.addUniversityToTeacher(university1);
//                teacher.addUniversityToTeacher(university2);
//                teacher.addUniversityToTeacher(university3);
//
//                entityManager.persist(teacher);

                // FIND
//
//                University university = entityManager.find(University.class, 1);
//                System.out.println(university);
//                System.out.println(university.getTeachers());

//                Teacher teacher = entityManager.find(Teacher.class, 4);
//                System.out.println(teacher);
//                System.out.println(teacher.getUniversities());

                //REMOVE

                // prepare to remove
//                Teacher teacher_id4 = entityManager.find(Teacher.class, 4);
//                University university_id1 = entityManager.find(University.class, 1);
//                teacher_id4.addUniversityToTeacher(university_id1);
//                entityManager.persist(teacher_id4);

//                Teacher teacher = entityManager.find(Teacher.class, 4);
//                entityManager.remove(teacher);

//                University university = entityManager.find(University.class, 4);
//                entityManager.remove(university);

                University university = entityManager.find(University.class, 1);
                entityManager.remove(university);

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
