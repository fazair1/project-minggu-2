package com.juaracoding;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    static final String JDBC_URL="jdbc:sqlserver://localhost:1433;database=university_db;encrypt=false;user=juaracodinguser;password=admin";

    // Database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }
//    DatabaseConnection conn = new DatabaseConnection();
    // Create Student
    public void addStudent(Student student) {
        String sql = "INSERT INTO Students (name, email, age, major, gpa) VALUES (?, ?, ?, ?, ?)";
        try ( Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getMajor());
            stmt.setDouble(5, student.getGpa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read All Students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("major"),
                        rs.getDouble("gpa")
                        );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Update Student
    public void updateStudent(Student student) {
        String sql = "UPDATE Students SET name = ?, email = ?, age = ?, major = ?, gpa = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getMajor());
            stmt.setDouble(5, student.getGpa());
            stmt.setInt(6, student.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Student
    public void deleteStudent(int id) {
        String sql = "DELETE FROM Students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search Student by Name
    public List<Student> searchStudentByName(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students WHERE name LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("major"),
                        rs.getDouble("gpa")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Search Student by Id
    public List<Student> searchStudentById(String id) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("major"),
                        rs.getDouble("gpa")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Search Student by Major
    public List<Student> searchStudentByMajor(String major) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students WHERE major LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + major + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("major"),
                        rs.getDouble("gpa")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Find Student by ID
    public boolean findStudentByID(String id) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("major"),
                        rs.getDouble("gpa")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (students.size()==0) {
            return false;
        } else {
            return true;
        }
    }

    // Average GPA
    public double getAverageGPA() {
        double average = 0.0;
        String sql = "SELECT AVG(gpa) AS average FROM Students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                average = rs.getDouble("average");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return average;
    }

    // Student Count
    public int getStudentCount() {
        int count = 0;
        String sql = "SELECT COUNT(name) AS student_count FROM Students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                count = rs.getInt("student_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
