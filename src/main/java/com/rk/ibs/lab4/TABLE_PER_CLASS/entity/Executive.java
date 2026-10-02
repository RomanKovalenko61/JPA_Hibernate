package com.rk.ibs.lab4.TABLE_PER_CLASS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "executive_per_class")
public class Executive extends Employee {
    private double bonus;

    public Executive() {
    }

    public Executive(double salary, double bonus) {
        super(salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
