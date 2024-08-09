package com.example.demo.bo.custom;

import com.example.demo.bo.SuperBO;
import com.example.demo.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderBO extends SuperBO {

    String gnerateNewOrderID(Connection connection) throws SQLException;

    boolean purchseOrder(OrderDTO order, Connection connection) throws SQLException;
}
