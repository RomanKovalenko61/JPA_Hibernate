package com.rk.ibs.lab2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DEPARTMENT_2")
public class Department_2 {

    @EmbeddedId
    @AttributeOverride(name = "companyName", column = @Column(name = "C_NAME"))
    @AttributeOverride(name = "departmentName", column = @Column(name = "DEPR_NAME"))
    private DepartmentKey id;

    private String description;

    public Department_2() {
    }

    public Department_2(DepartmentKey id, String description) {
        this.id = id;
        this.description = description;
    }

    public DepartmentKey getId() {
        return id;
    }

    public void setId(DepartmentKey id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
