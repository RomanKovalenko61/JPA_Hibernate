package com.rk.ibs.lab3.entity;

import com.rk.ibs.lab3.entity.embeddables.Project;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;

    @ElementCollection
    @CollectionTable(name = "emp_projects", joinColumns = @JoinColumn(name = "emp_id"))
    private List<Project> projects;

    public Employee() {
    }

    public Employee(String name, Department department, List<Project> projects) {
        this.name = name;
        this.department = department;
        this.projects = projects;
    }

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
