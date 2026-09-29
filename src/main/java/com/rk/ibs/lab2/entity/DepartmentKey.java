package com.rk.ibs.lab2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DepartmentKey implements Serializable {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "department_name")
    private String departmentName;

    public DepartmentKey() {
    }

    public DepartmentKey(String companyName, String departmentName) {
        this.companyName = companyName;
        this.departmentName = departmentName;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentKey that = (DepartmentKey) o;
        return companyName.equals(that.companyName) && departmentName.equals(that.departmentName);
    }

    @Override
    public int hashCode() {
        int result = companyName.hashCode();
        result = 31 * result + departmentName.hashCode();
        return result;
    }
}
