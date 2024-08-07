package com.example.demo.dao.custom;

import com.example.demo.dao.CrudDAO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;

import java.sql.Connection;
import java.util.List;

public interface CustomerDAO extends CrudDAO {

    String saveCustomer(CustomerDTO customer, Connection connection) throws Exception;

    List<Customer> getCustomer(Connection connection) throws Exception;
}
