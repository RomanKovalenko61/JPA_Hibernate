package com.rk.relationship.one_to_one;

import com.rk.relationship.one_to_one.entity.Passport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OneToOneBi {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                // У студента в поле passport_id будет null
//                Student student = new Student("Isaac", "Sharp", 9.8);
//                Passport passport = new Passport("isaac.sharp@yahoo.com", 183, "blue");
//                passport.setStudent(student);


//                // PERSIST cascadeType.ALL
//
//                Student student = new Student("Frankie", "Perry", 5.8);
//                Passport passport = new Passport("frankie.perry@yahoo.com", 185, "brown");
//                passport.setStudent(student);
//                student.setPassport(passport);
//
//                entityManager.persist(passport);

//                // FIND cascadeType.ALL
//
//                Passport passport = entityManager.find(Passport.class, 2);
//                System.out.println(passport);
//                System.out.println(passport.getStudent());

//                // REMOVE cascadeType.ALL
//
//                Passport passport = entityManager.find(Passport.class, 2);
//                entityManager.remove(passport);

                // REMOVE cascadeType.PERSIST cascadeType.MERGE студент останется в базе

                Passport passport = entityManager.find(Passport.class, 1);
                passport.getStudent().setPassport(null); // удалит если разорвать связь иначе в логах будет только select
                entityManager.remove(passport);

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
