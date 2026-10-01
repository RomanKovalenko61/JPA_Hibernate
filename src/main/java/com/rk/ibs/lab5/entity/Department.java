package com.rk.ibs.lab5.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    public Department() {
    }

    public Department(String name, Company company) {
        this.name = name;
        this.company = company;
    }

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
