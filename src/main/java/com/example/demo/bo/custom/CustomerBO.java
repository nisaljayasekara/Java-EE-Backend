package com.example.demo.bo.custom;

import com.example.demo.bo.SuperBO;
import com.example.demo.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    String saveCustomer(CustomerDTO customer, Connection connection) throws Exception;

    List<CustomerDTO> getAllCustomer(Connection connection) throws Exception;
    boolean deleteCustomer(String id, Connection connection) throws Exception;

    boolean updateCustomer(String customerContact, CustomerDTO customer, Connection connection) throws SQLException;

}
