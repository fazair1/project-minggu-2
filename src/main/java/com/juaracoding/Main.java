package com.juaracoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();
        List<Student> studentList = new ArrayList<>();
        studentList = studentDAO.getAllStudents();
        showMenu("0", studentList, studentDAO);
    }
    static void showMenu (String num, List<Student> studentList, StudentDAO studentDAO) {
        Scanner scan = new Scanner(System.in);
        int menu=0;

        while (menu!=5) {

            System.out.println("=====Student Management System=====");
            System.out.println("1. Add Student");
            System.out.println("2. Read Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.println("=====-=====-=====-=====-=====-=====");

            do {
                System.out.print("Choose the menu[1-5]: ");

                num = scan.nextLine();
                num = num.trim();
                System.out.println();

                if (onlyDigits(num) && !(num.isEmpty())) {
                    menu = Integer.parseInt(num);
                }

            } while (!(onlyDigits(num)) || num.isEmpty() || (menu<=0 || menu>5) );

            switch (menu) {
                case 1:
                    createStudent(studentList, studentDAO);
                    break;

//                case 2:
//                    readProduct(listProduk);
//                    break;
//
//                case 3:
//                    updateProduct(listProduk);
//                    break;
//
//                case 4:
//                    deleteProduct(listProduk);
//                    break;

                case 5:
                    return;
            }
        }
    }
    static void createStudent (List<Student> studentList, StudentDAO studentDAO) {
        Scanner scan = new Scanner(System.in);
        String studentname;
        String studentEmail;
        int studentAge = 0;
        String studentMajor;
        double studentGpa = 0.0;

        System.out.println("=====-=====Create Student====-=====");
//        System.out.println("=====-=====-=====-=====-=====-=====");

        do {
            System.out.print("Insert Student Name [Not Nullable]: ");
            studentname = scan.nextLine();
            studentname = studentname.trim();

        } while (studentname.isEmpty());

        do {
            System.out.print("Insert Student Email [Not Nullable]: ");
            studentEmail = scan.nextLine();
            studentEmail = studentEmail.trim();

        } while (studentEmail.isEmpty());

        String age;
        do {
            System.out.print("Insert Student Age [Not Nullable|Is Digit|Age > 0]: ");
            age = scan.nextLine();
            age = age.trim();

            if (onlyDigits(age) && !(age.isEmpty())) {
                studentAge = Integer.parseInt(age);
            }
        } while (age.isEmpty() || !(onlyDigits(age)) || studentAge <= 0);

        do {
            System.out.print("Insert Student Major [Not Nullable]: ");
            studentMajor = scan.nextLine();
            studentMajor = studentMajor.trim();

        } while (studentMajor.isEmpty());

        String gpa;
        do {
            System.out.print("Insert Student GPA [Not Nullable|Is Digit|GPA > 0]: ");
            gpa = scan.nextLine();
            gpa = gpa.trim();

            try
            {
                studentGpa = Double.parseDouble(gpa);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please input a double!");
            }
        } while (gpa.isEmpty() || studentGpa <= 0);

        Student newStudent = new Student((studentList.size()), studentname, studentEmail, studentAge, studentMajor, studentGpa);
        studentDAO.addStudent(newStudent);

        System.out.println("Successfully created Student with data:");
        System.out.println("ID: "+newStudent.getId());
        System.out.println("Name: "+newStudent.getName());
        System.out.println("Email: "+newStudent.getEmail());
        System.out.println("Age: "+newStudent.getAge());
        System.out.println("Major: "+newStudent.getMajor());
        System.out.println("GPA: "+newStudent.getGpa());
    }
    static void readStudent (List<Student> studentList, StudentDAO studentDAO) {
//        System.out.println("=====-=====-=====-=====-=====-=====");
        System.out.println("=====-=====Read Student======-=====");
        Scanner scan = new Scanner(System.in);
        String temp;

        if (studentList.isEmpty()) {
            System.out.println("Data kosong");
            return;
        }
        showProductList(listProduk);
        do {
            System.out.print("Apakah anda ingin melakukan sorting? [y/n]: ");
            temp = scan.nextLine();
            temp = temp.trim();
        }while (!(temp.equals("y") || temp.equals("n")));

        if (temp.equals("n")) {
            return;
        }
        else {
            do {
                System.out.print("Apakah sorting berdasarkan nama, harga, atau kategori? [nama/harga/kategori]: ");
                temp = scan.nextLine();
                temp = temp.trim();

            }while (!(temp.equals("nama") || temp.equals("harga") || temp.equals("kategori")));
            if (temp.equals("nama")) {
                Collections.sort(listProduk, new SortbyName());
            } else if (temp.equals("harga")) {
                Collections.sort(listProduk, new SortbyPrice());
            } else if (temp.equals("kategori")) {
                Collections.sort(listProduk, new SortbyCategory());
            }
            System.out.println("Sorting berhasil!");
        }
    }

    static boolean onlyDigits(String s) {

        // Traverse each character in the string
        for (int i = 0; i < s.length(); i++) {

            // Check if the character is not a digit
            if (!Character.isDigit(s.charAt(i))) {

                // If any character is not a digit, return false
                return false;
            }
        }
        return true;  // If all characters are digits, return true
    }

}