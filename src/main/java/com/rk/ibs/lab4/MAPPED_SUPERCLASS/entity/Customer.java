package com.rk.ibs.lab4.MAPPED_SUPERCLASS.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "customer_mapped_superclass")
@Table(name = "customer_mapped_superclass")
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
