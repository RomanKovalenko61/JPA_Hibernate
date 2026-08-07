package com.rk;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCInsert {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String DB_USER = "jpauser";
    static final String DB_PASS = "jpapwd";

    static final String INSERT_SQL = """
            INSERT INTO students (name, surname, avg_grade) VALUES (?, ?, ?)
            """;

    public static void main(String[] args) {
        Student student = new Student("Chanel", "King", 9.1);

        try (var conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             var preparedStatement = conn.prepareStatement(INSERT_SQL);) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setDouble(3, student.getAvgGrade());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
