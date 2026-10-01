package com.rk.ibs.lab5;

import com.rk.ibs.lab5.entity.Company;
import com.rk.ibs.lab5.entity.Employee;
import jakarta.persistence.*;

import java.util.List;

public class Lab5Ex {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab5");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                System.out.println("----- FIND COMPANY ------");
                Company company = entityManager.find(Company.class, 1);
                company.setName("THE BEST WAY, co."); // update before execute query
                System.out.println(company);

                System.out.println("----- PRINT DEPARTMENTS FROM COMPANY ------");
                System.out.println(company.getDepartments());

                System.out.println("----- PRINT ALL EMPLOYEES FROM COMPANY ------");
                TypedQuery<Employee> query = entityManager.createQuery("SELECT emp FROM Employee emp " +
                        "JOIN emp.department d JOIN d.company c WHERE c.id = :id  ", Employee.class);
                query.setParameter("id", 1);
                List<Employee> employees = query.getResultList();
                System.out.println(employees);

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}
