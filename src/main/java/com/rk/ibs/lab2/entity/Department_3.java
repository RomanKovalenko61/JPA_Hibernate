package com.rk.ibs.lab2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DEPARTMENT_3")
@IdClass(DepartmentKey.class)
public class Department_3 {

    @Id
    private String companyName;

    @Id
    private String departmentName;

    @Column(name = "DESCRIPTION")
    private String description;

    public Department_3() {
    }

    public Department_3(String companyName, String departmentName, String description) {
        this.companyName = companyName;
        this.departmentName = departmentName;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
