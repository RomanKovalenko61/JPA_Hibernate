package com.rk.ibs.lab5;

import com.rk.ibs.lab5.entity.Company;
import com.rk.ibs.lab5.entity.Department;
import com.rk.ibs.lab5.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Lab5FillData {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab5");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                Employee employee1 = new Employee("Roy");
                Employee employee2 = new Employee("Rebecca");
                Employee employee3 = new Employee("John");

                Company company = new Company("BESTWAY corp.");
                Department department1 = new Department("dep #1", company);
                Department department2 = new Department("dep #2", company);
                Department department3 = new Department("dep #3", company);
                company.setDepartments(List.of(department1, department2, department3));

                department1.setEmployees(List.of(employee1));
                employee1.setDepartment(department1);

                department2.setEmployees(List.of(employee2, employee3));
                employee2.setDepartment(department2);
                employee3.setDepartment(department2);

                entityManager.persist(employee1);
                entityManager.persist(employee2);
                entityManager.persist(employee3);

                entityManager.persist(department1);
                entityManager.persist(department2);
                entityManager.persist(department3);

                entityManager.persist(company);

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }
    }
}
