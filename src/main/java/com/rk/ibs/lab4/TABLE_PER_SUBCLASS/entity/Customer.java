package com.rk.ibs.lab4.TABLE_PER_SUBCLASS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_joined")
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
