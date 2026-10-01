package com.rk.ibs.lab5.entity;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "COMPANY")
public class Company {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

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
