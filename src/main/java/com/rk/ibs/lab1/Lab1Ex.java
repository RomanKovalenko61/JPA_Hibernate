package com.rk.ibs.lab1;

import com.rk.ibs.lab1.entity.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Lab1Ex {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                // сохраняем данные одной сущности в две таблицы
                Company company = new Company("MTS", "127000, Moscow, Gagarin street, 1");
                entityManager.persist(company);

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
