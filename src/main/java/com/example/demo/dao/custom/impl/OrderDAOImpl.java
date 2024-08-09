package com.example.demo.dao.custom.impl;

import com.example.demo.dao.custom.OrderDAO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public static String SAVE_ORDER = "INSERT INTO orders (oid,date,customerID) VALUES(?,?,?)";

    public static String GET_ALL_ORDERS = "SELECT * FROM orders ";

    @Override
    public String saveOrder(OrderDTO order, Connection connection) throws SQLException {

        try{
            var sc = connection.prepareStatement(SAVE_ORDER);
            sc.setString(1,order.getOid());
            sc.setString(2, String.valueOf(order.getDate()));
            sc.setString(3,order.getCustomerID());

            if(sc.executeUpdate() != 0){
                return "Order Save Successfully";
            }else {
                return "Failed to Save Student";
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public String saveOrder(OrderDetails entity, Connection connection) throws SQLException {
        return null;
    }

    public List<Order> getAllOrders(Connection connection) throws Exception {
        try {
            OrderDTO ordersDTO = new OrderDTO();
            var sc = connection.prepareStatement(GET_ALL_ORDERS);
            ResultSet rst = sc.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();

            while (rst.next()){
                orders.add(new Order(
                        rst.getString("oid"),
                        rst.getString("date"),
                        rst.getString("customerID")
                ));
            }
            return orders;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }


    @Override
    public boolean saveOrder(Order orders, Connection connection) {
        return false;
    }

    @Override
    public String generateNextId(Connection connection) throws SQLException {
        String sql = "SELECT oid FROM orders ORDER BY oid DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql, connection);
        if (resultSet.next()) {
            String oid = resultSet.getString(1);
            return oid;
        }
        return null;
    }

}
