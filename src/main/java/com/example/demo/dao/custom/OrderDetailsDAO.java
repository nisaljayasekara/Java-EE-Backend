package com.example.demo.dao.custom;

import com.example.demo.dao.CrudDAO;
import com.example.demo.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends CrudDAO {

    List<OrderDetails> getAllOrderDetails(Connection connection) throws Exception;

    boolean save(Connection connection, OrderDetails orderDetails) throws SQLException;


}
