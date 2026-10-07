package com.rk.ibs.lab6.service;

import com.rk.ibs.lab6.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EntityService1 extends EntityService {

    @Override
    public List<Employee> getEmployeesByDepartmentName(String name) {
        EntityManagerFactory factory = getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Employee> query = entityManager.createNamedQuery("findEmployeesByDepartmentName", Employee.class);
        query.setParameter("name", name);
        List<Employee> list = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }

    @Override
    public List<DepartmentInfo> getDepartmentsInfo() {
        EntityManagerFactory factory = getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<DepartmentInfo> query = entityManager.createQuery("select new com.rk.ibs.lab6.service.DepartmentInfo(d.name, size(d.employees)) from Department d", DepartmentInfo.class);
        List<DepartmentInfo> list = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return list;
    }
}
