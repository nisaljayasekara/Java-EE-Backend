package com.example.demo.bo.custom.impl;

import com.example.demo.bo.custom.CustomerBO;
import com.example.demo.dao.DAOFactory;
import com.example.demo.dao.custom.CustomerDAO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_DAO);
    @Override
    public String saveCustomer(CustomerDTO customer, Connection connection) throws Exception {
        return customerDAO.saveCustomer(customer,connection);

    }
    public List<CustomerDTO> getAllCustomer(Connection connection) throws Exception {
        List<Customer> customersList = customerDAO.getCustomer(connection);
        List<CustomerDTO> customerDTOS = new ArrayList<>();


        for (Customer customer : customersList) {
            customerDTOS.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            ));
        }
        return customerDTOS;
       }
    public boolean deleteCustomer(String id, Connection connection) throws Exception{
        return customerDAO.deleteCustomer(id,connection);
    }
    public boolean updateCustomer(String customerContact, CustomerDTO customer, Connection connection) throws SQLException {
        return customerDAO.updateCustomer(customerContact,customer,connection);
    }
}
