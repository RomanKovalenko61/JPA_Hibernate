package com.rk.relationship.one_to_one;

import com.rk.relationship.one_to_one.entity.Passport;
import com.rk.relationship.one_to_one.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EnumEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

//                Student student = new Student("Eric", "Scott", 7.4);
//                Passport passport = new Passport("eric.scott@yahoo.com", 173, EyeColor.GREEN);
//                student.setPassport(passport);
//
//                entityManager.persist(student);

                Student student = entityManager.find(Student.class, 1);
                System.out.println(student.getPassport());

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
