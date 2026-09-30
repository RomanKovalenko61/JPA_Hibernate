package com.rk.ibs.lab4.TABLE_PER_SUBCLASS;


import com.rk.ibs.lab4.TABLE_PER_SUBCLASS.entity.Customer;
import com.rk.ibs.lab4.TABLE_PER_SUBCLASS.entity.Employee;
import com.rk.ibs.lab4.TABLE_PER_SUBCLASS.entity.Executive;
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
