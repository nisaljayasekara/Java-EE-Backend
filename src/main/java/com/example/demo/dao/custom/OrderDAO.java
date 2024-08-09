package com.example.demo.dao.custom;

import com.example.demo.dao.CrudDAO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO {

    String saveOrder(OrderDTO order, Connection connection) throws SQLException;

    String saveOrder(OrderDetails entity, Connection connection) throws SQLException;


    boolean saveOrder(Order orders, Connection connection);

    String generateNextId(Connection connection) throws SQLException;
}
