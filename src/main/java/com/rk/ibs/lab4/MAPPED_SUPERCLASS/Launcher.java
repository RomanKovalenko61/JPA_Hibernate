package com.rk.ibs.lab4.MAPPED_SUPERCLASS;

import com.rk.ibs.lab4.MAPPED_SUPERCLASS.entity.Customer;
import com.rk.ibs.lab4.MAPPED_SUPERCLASS.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Launcher {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                Customer customer = new Customer(0.2f);
                Employee employee = new Employee(2000f);

                entityManager.persist(customer);
                entityManager.persist(employee);

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}

