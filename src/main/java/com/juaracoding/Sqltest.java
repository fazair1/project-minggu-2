package com.juaracoding;

import java.sql.DriverManager;

public class Sqltest {
    static final String JDBC_URL="jdbc:sqlserver://localhost:1433;database=employee;encrypt=false;user=juaracodinguser;password=admin";
    public static void main(String[] args) throws Exception {
        String createTableSql = "INSERT INTO Employees (name, email, department, salary) VALUES ('fauzan', 'test@gmail.com', 'dep1', 100)";
        var connection = DriverManager.getConnection(JDBC_URL);
        var statement = connection.createStatement();
        statement.execute(createTableSql);

        System.out.println("table created");
    }
}
