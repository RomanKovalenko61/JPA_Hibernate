package com.rk.inheritance_mapping.entity;

import jakarta.persistence.*;

//@Entity
//@Table(name = "employees") // для joined strategy
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // default InheritanceType.SINGLE_TABLE
//@DiscriminatorColumn(name = "emp_type") // для SINGLE_TABLE имя столбца с названием класса сущности

@MappedSuperclass // @Entity убрать они противоречат друг другу
public abstract class Employee {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TABLE в случае сохр наследников в отдельные таблицы (TABLE_PER_CLASS)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "experience")
    private Double experience;

    public Employee() {
    }

    public Employee(String name, Integer salary, Double experience) {
        this.name = name;
        this.salary = salary;
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }
}
