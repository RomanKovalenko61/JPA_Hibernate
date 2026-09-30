package com.rk.ibs.lab3;

import com.rk.ibs.lab3.entity.Company;
import com.rk.ibs.lab3.entity.Department;
import com.rk.ibs.lab3.entity.Employee;
import com.rk.ibs.lab3.entity.embeddables.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Lab3Ex {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                Company company = new Company("Banana");
                Department department1 = new Department("Sales", company);
                Department department2 = new Department("Production", company);
                company.setDepartments(List.of(department1, department2));

                Project project1 = new Project("project #1");
                Project project2 = new Project("project #2");
                Project project3 = new Project("project #3");
                Project project4 = new Project("project #4");
                Project project5 = new Project("project #5");
                Project project6 = new Project("project #6");

                Employee emp1 = new Employee("Rebecca", department1, List.of(project1, project2));
                department1.setEmployees(List.of(emp1));

                Employee emp2 = new Employee("John", department2, List.of(project3, project4));
                Employee emp3 = new Employee("Roy", department2, List.of(project5));
                Employee emp4 = new Employee("Isaac", department2, List.of(project6));
                department2.setEmployees(List.of(emp2, emp3, emp4));

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
