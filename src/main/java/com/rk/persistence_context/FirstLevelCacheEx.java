package com.rk.persistence_context;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FirstLevelCacheEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                // One select  teacher will be one (in cache)
                // Repeatable Read
                Teacher teacher = entityManager.find(Teacher.class, 3);
//                Teacher teacher2 = entityManager.find(Teacher.class, 3);
                transaction.commit();

                transaction.begin();

                // все равно один select т.к. persistence context тот же
                // если создать еще один entityManager, то будет два запроса
                // т.к. у каждого entityManager свой persistence context
                Teacher teacher2 = entityManager.find(Teacher.class, 3);

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
