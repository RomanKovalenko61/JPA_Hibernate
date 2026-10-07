package com.rk.ibs.lab6.entity;


import jakarta.persistence.*;

@Entity
@NamedQuery(name = "findEmployeeByName", query = "select e from Employee e where e.name = :name")
@NamedQuery(name = "findEmployeesByDepartmentName", query = "select e from Employee e where e.department.name = :name")
@NamedQuery(name = "findEmployeesByCompanyName", query = "select e from Employee e where e.department.company.name = :name")
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToOne
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
