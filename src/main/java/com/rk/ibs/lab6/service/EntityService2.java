package com.rk.ibs.lab6.service;

import com.rk.ibs.lab6.entity.Department;
import com.rk.ibs.lab6.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class EntityService2 extends EntityService {
    @Override
    public List<Employee> getEmployeesByDepartmentName(String name) {
        EntityManagerFactory factory = getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Department> department = cq.from(Department.class);
        Join<Department, Employee> employees = department.join("employees");

        cq.select(employees).where(cb.equal(department.get("name"), name));
        List<Employee> list = entityManager.createQuery(cq).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }

    @Override
    public List<DepartmentInfo> getDepartmentsInfo() {
        EntityManagerFactory factory = getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DepartmentInfo> cq = cb.createQuery(DepartmentInfo.class);

        Root<Employee> employee = cq.from(Employee.class);
        cq.select(cb.construct(DepartmentInfo.class, employee.get("department").get("name"), cb.count(employee.get("department"))));

        cq.groupBy(employee.get("department"));
        List<DepartmentInfo> list = entityManager.createQuery(cq).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }
}
