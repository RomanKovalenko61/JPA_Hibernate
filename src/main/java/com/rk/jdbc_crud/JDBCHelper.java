package com.rk.jdbc_crud;

import com.rk.entity.Student;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    static final String SELECT_BY_AVG_GRADE_SQL = """
            SELECT * FROM students WHERE avg_grade >= ?;
            """;

    static final String DELETE_BY_SURNAME_SQL = """
                    DELETE FROM students WHERE surname = ?;
            """;

    public static void main(String[] args) {
        Student student = new Student("Chanel", "King", 9.1);
        Student student1 = new Student("Roman", "Smith", 5.1);
        save(student);
        System.out.println("Student after save " + student);
        save(student1);
        update(student1, 9.0);

        System.out.println(getByAvgGrade(8.5));
        System.out.println(deleteBySurname("Smith"));
    }

    private static void save(Student student) {
        try (var conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             var preparedStatement = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setDouble(3, student.getAvgGrade());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to save student");
            }

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                student.setId(id);
                System.out.println("student set id: " + id);
            } else {
                throw new SQLException("Failed to set ID for student");
            }
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

    private static List<Student> getByAvgGrade(double avgGrade) {
        try (var conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             var preparedStatement = conn.prepareStatement(SELECT_BY_AVG_GRADE_SQL)) {
            preparedStatement.setDouble(1, avgGrade);

            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(build(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Student build(ResultSet resultSet) throws SQLException {
        return new Student(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getDouble("avg_grade"));
    }

    private static boolean deleteBySurname(String surname) {
        try (var conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             var preparedStatement = conn.prepareStatement(DELETE_BY_SURNAME_SQL)) {
            preparedStatement.setString(1, surname);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
