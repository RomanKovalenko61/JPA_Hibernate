package com.rk.ibs.lab4.TABLE_PER_CLASS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name="employee_per_class")
@Table(name="employee_per_class")
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
