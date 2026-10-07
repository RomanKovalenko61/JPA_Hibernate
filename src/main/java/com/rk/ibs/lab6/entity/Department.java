package com.rk.ibs.lab6.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name = "findDepartmentByName", query = "select d from Department d where d.name = :name")
@NamedQuery(name = "findDepartmentsByCompanyName", query = "select d from Department d where d.company.name = :name")
public class Department {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;


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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
