package com.rk.advanced_mapping;

import com.rk.advanced_mapping.entity.Employee;
import com.rk.advanced_mapping.entity.Friend;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class ListMappingFriendEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

//                List<Friend> friendList = new ArrayList<>();
//                friendList.add(new Friend("Chanel", "King", 22));
//                friendList.add(new Friend("Leo", "Farrell", 24));
//                friendList.add(new Friend("Julia", "Deen", 23));
//
//                Employee employee = new Employee("Michael", 4000, 15d, friendList);
//
//                entityManager.persist(employee);

                Employee emp = entityManager.find(Employee.class, 1);
                System.out.println(emp);

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
