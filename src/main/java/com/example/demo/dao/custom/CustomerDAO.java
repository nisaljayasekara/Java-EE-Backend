package com.example.demo.dao.custom;

import com.example.demo.dao.CrudDAO;
import com.example.demo.dto.CustomerDTO;

import java.sql.Connection;

public interface CustomerDAO extends CrudDAO {

    String saveCustomer(CustomerDTO customer, Connection connection) throws Exception;
}
