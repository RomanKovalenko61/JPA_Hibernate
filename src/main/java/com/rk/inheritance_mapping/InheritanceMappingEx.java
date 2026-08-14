package com.rk.inheritance_mapping;

import com.rk.inheritance_mapping.entity.Driver;
import com.rk.inheritance_mapping.entity.Employee;
import com.rk.inheritance_mapping.entity.Teacher;
import jakarta.persistence.*;

import java.util.List;

public class InheritanceMappingEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

//                Teacher teacher = new Teacher("Alessandro", 2500, 8d, "CS", true);
//                Driver driver = new Driver("Peter", 2300, 15d, 'B', "BMW");

//                Teacher teacher = new Teacher("Rio", 2000, 3d, "Biology", false);
//                Driver driver = new Driver("Michael", 2800, 28d, 'C', "Mercedes");

//                entityManager.persist(teacher);
//                entityManager.persist(driver);

                // Запрос не работает при @MappedSuperclass над Employee
//                Query query = entityManager.createQuery("SELECT emp FROM Employee emp");
//                List<Employee> employees = query.getResultList();
//                System.out.println(employees);

                Query query1 = entityManager.createQuery("SELECT dr FROM Driver dr");
                List<Driver> drivers = query1.getResultList();
                System.out.println(drivers);

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
