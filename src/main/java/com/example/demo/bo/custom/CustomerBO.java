package com.example.demo.bo.custom;

import com.example.demo.bo.SuperBO;
import com.example.demo.dto.CustomerDTO;

import java.sql.Connection;

public interface CustomerBO extends SuperBO {
    String saveCustomer(CustomerDTO customer, Connection connection) throws Exception;

}
