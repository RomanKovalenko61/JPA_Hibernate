package com.rk.persistence_context.jpa_methods;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FlushEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

//                Teacher teacher1 = new Teacher("Alessandro", "Lozano", "CS", true);
//                Teacher teacher2 = new Teacher("Rio", "Berger", "Biology", false);
//                Teacher teacher3 = new Teacher("Landry", "Shelton", "Math", true);
//
//                entityManager.persist(teacher1);
//                entityManager.persist(teacher2);
//                entityManager.persist(teacher3);


//                Teacher teacher1 = entityManager.find(Teacher.class, 1);
//                Teacher teacher2 = entityManager.find(Teacher.class, 2);
//                Teacher teacher3 = entityManager.find(Teacher.class, 3);
//
//                System.out.println("Update teacher 1");
//                teacher1.setSubject("Economics");
//
//                System.out.println("Delete teacher 2");
//                entityManager.remove(teacher2);
//
//                System.out.println("Flush");
//                entityManager.flush();
//
//                System.out.println("Delete teacher 3");
//                teacher3.setProfessor(false);

                Teacher teacher1 = entityManager.find(Teacher.class, 1);
                Teacher teacher3 = entityManager.find(Teacher.class, 3);

                teacher1.setSubject("Biology");
                entityManager.flush();
                teacher3.setSubject("Biology");

                Teacher teacherX = entityManager.find(Teacher.class, 100);
                System.out.println(teacherX.getName());

//                System.out.println("Commit");
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
