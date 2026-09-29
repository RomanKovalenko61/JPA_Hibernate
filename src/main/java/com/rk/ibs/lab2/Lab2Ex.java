package com.rk.ibs.lab2;

import com.rk.ibs.lab2.entity.DepartmentKey;
import com.rk.ibs.lab2.entity.Department_1;
import com.rk.ibs.lab2.entity.Department_2;
import com.rk.ibs.lab2.entity.Department_3;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Lab2Ex {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                // MySQL не поддерживает seq поэтому, создается таблица даже если указана стратегия SEQUENCE
                // Лучше всего использовать IDENTITY
                // В данном случае используем глобальную таблицу для всех entity. См package-info.java
                Department_1 department1 = new Department_1("IBM", "IT", "IT engineers");
                entityManager.persist(department1);

                // Используем @EmbeddedId и переписываем имена колонок определенные в DepartmentKey
                Department_2 department2 =
                        new Department_2(new DepartmentKey("Apple", "Marketing"),
                                "the best");

                entityManager.persist(department2);

                // Используем @IdClass имена колонок Primary Key берем с DepartmentKey
                Department_3 department3 =
                        new Department_3("Tesla", "CS", "make world better");

                entityManager.persist(department3);

                transaction.commit();
            } catch (Exception e) {
//                e.printStackTrace();
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}
