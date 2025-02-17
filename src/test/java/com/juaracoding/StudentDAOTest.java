package com.juaracoding;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class StudentDAOTest {
    private StudentDAO studentDAO;
    private Student testStudent;

    @BeforeClass
    public void setUp() {
        studentDAO = new StudentDAO();
        // Create a test student
        testStudent = new Student(1, "Test User", "test.user@example.com", 20, "IT", 3.0);
        studentDAO.addStudent(testStudent);
    }

    @AfterClass
    public void tearDown() {
        // Clean up the test data
        if (testStudent != null) {
            studentDAO.deleteStudent(testStudent.getId());
        }
    }

    @Test
    public void testAddStudent() {
        Student newStudent = new Student(2, "Test User2", "test.user2@example.com", 21, "Computer Science", 3.2);
        studentDAO.addStudent(newStudent);

        // Verify the student was added
        List<Student> students = studentDAO.getAllStudents();
        boolean found = students.stream().anyMatch(emp -> emp.getEmail().equals(newStudent.getEmail()));

        // Clean up
        studentDAO.deleteStudent(newStudent.getId());
        Assert.assertTrue(found, "Student was not added to the database.");
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        Assert.assertNotNull(students, "Student list should not be null.");
        Assert.assertFalse(students.isEmpty(), "Student list should not be empty.");
    }

    @Test
    public void testUpdateStudent() {
        // Update the test student's major
        testStudent.setMajor("Engineering");
        studentDAO.updateStudent(testStudent);

        // Verify the update
        List<Student> students = studentDAO.getAllStudents();
        Student updatedStudent = students.stream()
                .filter(emp -> emp.getId() == testStudent.getId())
                .findFirst()
                .orElse(null);

        Assert.assertNotNull(updatedStudent, "Student should exist in the database.");
        Assert.assertEquals(updatedStudent.getMajor(), "Engineering", "Major was not updated.");
    }

    @Test
    public void testDeleteStudent() {
        // Add a new student to delete
        Student studentToDelete = new Student(3, "Temp Student", "temp.student@example.com", 22, "Temp", 3.3);
        studentDAO.addStudent(studentToDelete);

        // Delete the employee
        studentDAO.deleteStudent(studentToDelete.getId());

        // Verify the employee was deleted
        List<Student> students = studentDAO.getAllStudents();
        boolean found = students.stream().anyMatch(emp -> emp.getId() == studentToDelete.getId());
        Assert.assertFalse(found, "Student was not deleted from the database.");
    }

    @Test
    public void testSearchStudentByName() {
        // Search for the test student by name
        List<Student> searchResults = studentDAO.searchStudentByName("Test User");
        Assert.assertFalse(searchResults.isEmpty(), "Search results should not be empty.");

        // Verify the search result
        Student foundStudent = searchResults.get(0);
        Assert.assertEquals(foundStudent.getName(), testStudent.getName(), "Search result name does not match.");
        Assert.assertEquals(foundStudent.getEmail(), testStudent.getEmail(), "Search result email does not match.");
    }

    @Test
    public void testSearchStudentById() {
        // Search for the test student by id
        List<Student> searchResults = studentDAO.searchStudentById("1");
        Assert.assertFalse(searchResults.isEmpty(), "Search results should not be empty.");

        // Verify the search result
        Student foundStudent = searchResults.get(0);
        Assert.assertEquals(foundStudent.getId(), testStudent.getId(), "Search result id does not match.");
        Assert.assertEquals(foundStudent.getEmail(), testStudent.getEmail(), "Search result email does not match.");
    }

    @Test
    public void testFindStudentById() {
        // Find the test student by id
        boolean findResult = studentDAO.findStudentByID("1");
        Assert.assertTrue(findResult, "Search results should not be false.");
    }
}
