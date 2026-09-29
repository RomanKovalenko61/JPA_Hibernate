package com.rk.ibs.lab2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DEPARTMENT_1")
public class Department_1 {

    @Id
    // Лучше использовать IDENTITY
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    // MySQL не поддерживает sequence поэтому все равно создается таблица
    // package-info лежит общий генератор, или настраиваем для конкретной сущности
    // (копипаста, но можем указать свои значения в колонках: имя, стартовое знач + приращение)
//    @SequenceGenerator(name = "department_gen", sequenceName = "DEPARTMENT_1_SEQ", initialValue = 10, allocationSize = 30)
//    @TableGenerator(
//            name = "department_gen",
//            table = "id_generator",
//            pkColumnName = "gen_name",
//            valueColumnName = "gen_val",
//            pkColumnValue = "department_id",
//            initialValue = 10,
//            allocationSize = 30
//    )
    @Column(name = "ID")
    private int id;

    private String companyName;
    private String departmentName;
    private String description;

    public Department_1() {
    }

    public Department_1(String companyName, String departmentName, String description) {
        this.companyName = companyName;
        this.departmentName = departmentName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
