package com.rk.ibs.lab1.entity;

import jakarta.persistence.*;

@Table(name = "company")
@SecondaryTable(
        name = "company_detail",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
)
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "company_name", table = "company")
    private String name;

    @Column(name = "company_address", table = "company_detail")
    private String address;

    public Company() {
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
