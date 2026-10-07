package com.rk.ibs.lab6.service;

public class DepartmentInfo {
    private final String departmentName;
    private final long employeesCount;

    public DepartmentInfo(String departmentName, long employeesCount) {
        this.departmentName = departmentName;
        this.employeesCount = employeesCount;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }
}
