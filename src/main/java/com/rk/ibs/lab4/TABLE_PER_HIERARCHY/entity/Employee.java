package com.rk.ibs.lab4.TABLE_PER_HIERARCHY.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "employee_single_table_person")
@DiscriminatorValue("Employee")
public class Employee extends Person {
    private double salary;

    public Employee() {
    }

    public Employee(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
