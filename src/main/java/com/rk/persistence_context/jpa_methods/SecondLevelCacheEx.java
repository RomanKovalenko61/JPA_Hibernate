package com.rk.persistence_context.jpa_methods;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SecondLevelCacheEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course")) {

            try {
                EntityManager entityManager1 = factory.createEntityManager();
                System.out.println("FIRST Time");
                Teacher teacher1 = entityManager1.find(Teacher.class, 4);
                System.out.println(teacher1);
                entityManager1.close();

                EntityManager entityManager2 = factory.createEntityManager();
                System.out.println("SECOND Time");
                Teacher teacher2 = entityManager2.find(Teacher.class, 4);
                System.out.println(teacher2);
                entityManager2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
