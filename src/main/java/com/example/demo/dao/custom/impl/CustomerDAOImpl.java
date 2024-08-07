package com.example.demo.dao.custom.impl;

import com.example.demo.dao.custom.CustomerDAO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public static String GET_CUSTOMER = "SELECT * FROM customers";
    public static String SAVE_CUSTOMER = "INSERT INTO customers (id,name,address,salary) VALUES(?,?,?,?)";
    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws SQLException {
        try{
            var sc = connection.prepareStatement(SAVE_CUSTOMER);
            sc.setString(1,customer.getId());
            sc.setString(2,customer.getName());
            sc.setString(3,customer.getAddress());
            sc.setString(4, customer.getSalary());
            if(sc.executeUpdate() != 0){
                return "Customer Save Successfully";
            }else {
                return "Failed to Save Student";
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
    }
}
    public List<Customer> getCustomer(Connection connection) throws Exception {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            var sc = connection.prepareStatement(GET_CUSTOMER);
            ResultSet rst = sc.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();

            while (rst.next()){
                customers.add(new Customer(
                        rst.getString("id"),
                        rst.getString("name"),
                        rst.getString("address"),
                        rst.getString("salary")));
            }
            return customers;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
