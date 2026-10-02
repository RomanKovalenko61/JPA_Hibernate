package com.rk.ibs.lab4.TABLE_PER_CLASS;


import com.rk.ibs.lab4.TABLE_PER_CLASS.entity.Customer;
import com.rk.ibs.lab4.TABLE_PER_CLASS.entity.Employee;
import com.rk.ibs.lab4.TABLE_PER_CLASS.entity.Executive;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Launcher {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab4.TABLE_PER_CLASS");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                // Generator ID общий для всех, сущности в своих таблицах
                Customer customer = new Customer(0.2f);
                Employee employee = new Employee(2000f);
                Executive executive = new Executive(1500f, 0.15f);

                entityManager.persist(customer);
                entityManager.persist(employee);
                entityManager.persist(executive);

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}

