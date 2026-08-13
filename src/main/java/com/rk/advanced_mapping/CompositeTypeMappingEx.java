package com.rk.advanced_mapping;

import com.rk.advanced_mapping.entity.Address;
import com.rk.advanced_mapping.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CompositeTypeMappingEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                Address address = new Address("USA", "Chicago", "Dempster", 40);
                Employee employee = new Employee("Michael", 4000, 15d, address);
                entityManager.persist(employee);

//                Employee employee = entityManager.find(Employee.class, 1);
//                System.out.println(employee);

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
