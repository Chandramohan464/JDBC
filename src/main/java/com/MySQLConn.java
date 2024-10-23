package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MySQLConn {
    
    public Connection getConnection()throws SQLException{
        String url="jdbc:mysql://localhost:3306/employee";
        String user="root";
        String password="C.mohan@81";
        return DriverManager.getConnection(url,user,password);
    }

    public void createTable(){
        String sQLQuery="create table employees(id int primary key,name varchar(25),age int)";

        try (Connection connection=getConnection();
        Statement statement = connection.createStatement()) {
            statement.executeUpdate(sQLQuery);
            System.out.println("Table created Successfully");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void createEmployee(Employee employee){
        String sqlQuery = "insert into employees(id,name,age) values (?,?,?)";

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){

            preparedStatement.setInt(1,employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3,employee.getAge());

            preparedStatement.executeUpdate();
            System.out.println("Employee added successfully");

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void readEmployee(){
        List<Employee> employeeList = new ArrayList<>();
        String sqlQuery = "select * from employees";

        try(Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery)){
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Employee employee = new Employee(id,name,age);
                employeeList.add(employee);
                System.out.println("ID: " +id+ ", Name: " +name+ ", Age: " +age);
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
