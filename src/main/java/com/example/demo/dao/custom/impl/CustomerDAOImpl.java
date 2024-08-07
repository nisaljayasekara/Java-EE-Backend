package com.example.demo.dao.custom.impl;

import com.example.demo.dao.custom.CustomerDAO;
import com.example.demo.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
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
}
