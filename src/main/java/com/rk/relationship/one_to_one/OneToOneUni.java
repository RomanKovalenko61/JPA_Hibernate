package com.rk.relationship.one_to_one;

import com.rk.relationship.one_to_one.entity.Passport;
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

                Student student1 = new Student("Chanel", "King", 9.1);
                Passport passport1 = new Passport("chanel.king@gmail.com", 174, "blue");
                student1.setPassport(passport1);

                entityManager.persist(passport1);
                entityManager.persist(student1);

                transaction.commit();
            } catch (Exception ex) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}
