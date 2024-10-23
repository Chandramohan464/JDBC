package com;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MySQLConn conn=new MySQLConn();
        //System.out.println("Connection Successful"+conn.getConnection());
        //conn.createTable();
        //conn.createEmployee(new Employee(1,"Chandramohan",24));
        //conn.createEmployee(new Employee(2,"Abhishek",26));
        //conn.createEmployee(new Employee(3,"Shubam",27));
        conn.readEmployee();
    }
}
