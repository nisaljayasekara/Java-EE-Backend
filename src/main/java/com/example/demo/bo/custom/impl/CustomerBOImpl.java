package com.example.demo.bo.custom.impl;

import com.example.demo.bo.custom.CustomerBO;
import com.example.demo.dao.DAOFactory;
import com.example.demo.dao.custom.CustomerDAO;
import com.example.demo.dto.CustomerDTO;

import java.sql.Connection;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_DAO);
    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws Exception {
        return customerDAO.saveCustomer(customer,connection);
    }
}
