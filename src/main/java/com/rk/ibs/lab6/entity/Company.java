package com.rk.ibs.lab6.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name = "findCompanyByName", query = "select c from Company c where c.name = :name")
public class Company {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Department> departments;


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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
