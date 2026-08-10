package com.rk.relationship.one_to_one;

import com.rk.relationship.one_to_one.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OneToOneUni {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                // PERSIST

//                Student student1 = new Student("Isaac", "Sharp", 9.8);
//                Passport passport1 = new Passport("isaac.sharp@yahoo.com", 183, "blue");
//                student1.setPassport(passport1);
//
////                entityManager.persist(passport1); // сохраняли без использования каскадных операций
//                entityManager.persist(student1);

//                // FIND // не обязательно открытие транзакции т.к. не меняем данные
//
//                Student student = entityManager.find(Student.class, 20);
//                System.out.println(student);
//                System.out.println(student.getPassport());

//                // REMOVE
//
//                Student student = entityManager.find(Student.class, 2);
//                entityManager.remove(student);

//                // UPDATE
//
//                Student student = entityManager.find(Student.class, 3);
//                student.getPassport().setEmail("x@gmail.com");

                transaction.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}
