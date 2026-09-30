package com.rk.ibs.lab4.TABLE_PER_CLASS.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "custoner_per_class")
@Table(name = "custoner_per_class")
public class Customer extends Person {
    private double discount;

    public Customer() {
    }

    public Customer(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
