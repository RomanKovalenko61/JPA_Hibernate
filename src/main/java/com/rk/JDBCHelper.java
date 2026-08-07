package com.rk;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCHelper {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String DB_USER = "jpauser";
    static final String DB_PASS = "jpapwd";

    static final String INSERT_SQL = """
            INSERT INTO students (name, surname, avg_grade) VALUES (?, ?, ?)
            """;

    static final String UPDATE_SQL = """
            UPDATE students SET avg_grade = ? WHERE name = ?;
            """;

    public static void main(String[] args) {
        Student student = new Student("Chanel", "King", 9.1);
        Student student1 = new Student("Roman", "Smith", 5.1);
        save(student);
        save(student1);
        update(student1, 9.0);
    }

    private static void save(Student student) {
        try (var conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             var preparedStatement = conn.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setDouble(3, student.getAvgGrade());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void update(Student student, double newAvgGrade) {
        try (var conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             var preparedStatement = conn.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setDouble(1, newAvgGrade);
            preparedStatement.setString(2, student.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
