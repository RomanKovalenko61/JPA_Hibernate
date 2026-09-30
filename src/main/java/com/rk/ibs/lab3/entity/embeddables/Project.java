package com.rk.ibs.lab3.entity.embeddables;


import jakarta.persistence.Embeddable;


@Embeddable
public class Project {
    private String name;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
