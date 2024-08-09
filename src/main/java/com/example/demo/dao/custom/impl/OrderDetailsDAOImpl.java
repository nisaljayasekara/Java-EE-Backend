package com.example.demo.dao.custom.impl;

import com.example.demo.dao.custom.OrderDAO;
import com.example.demo.dao.custom.OrderDetailsDAO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailsDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    public static String GET_ALL_ORDERDETAILS = "SELECT * FROM orderdetails";
    public List<OrderDetails> getAllOrderDetails(Connection connection) throws Exception {
        try {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
            var sc = connection.prepareStatement(GET_ALL_ORDERDETAILS);
            ResultSet rst = sc.executeQuery();
            ArrayList<OrderDetails> orderDetails = new ArrayList<>();

            while (rst.next()){
                orderDetails.add(new OrderDetails(
                        rst.getString("oid"),
                        rst.getString("code"),
                        rst.getInt("qty"),
                        rst.getInt("payment")
                ));
            }
            return orderDetails;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean save(Connection connection, OrderDetails entity) throws SQLException {
        String sql = "INSERT INTO orderdetails VALUES(?, ?, ?,?)";
        return SQLUtil.execute(sql, connection,
                entity.getOid(),
                entity.getCode(),
                entity.getQty(),
                entity.getPayment());
    }

}
