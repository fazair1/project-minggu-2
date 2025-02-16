package com.juaracoding;

public class Student {
    private int id;
    private String name;
    private String email;
    private int age;
    private String major;
    private double gpa;

    public Student(int id, String name, String email, int age, String major, double gpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.major = major;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
