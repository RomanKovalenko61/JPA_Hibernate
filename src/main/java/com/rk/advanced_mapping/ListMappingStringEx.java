package com.rk.advanced_mapping;

import com.rk.advanced_mapping.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class ListMappingStringEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                List<String> friendsNames = new ArrayList<>();
                friendsNames.add("Roy");
                friendsNames.add("Kynlee");
                friendsNames.add("Eric");

                Employee employee = new Employee("Rudolf", 3500, 10d, friendsNames);

                entityManager.persist(employee);

//                Employee emp = entityManager.find(Employee.class, 1);
//                System.out.println(emp.getFriends());

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
