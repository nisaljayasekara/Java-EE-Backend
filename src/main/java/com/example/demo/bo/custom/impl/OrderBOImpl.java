package com.example.demo.bo.custom.impl;

import com.example.demo.bo.custom.OrderBO;
import com.example.demo.dao.DAOFactory;
import com.example.demo.dao.custom.ItemDAO;
import com.example.demo.dao.custom.OrderDAO;
import com.example.demo.dao.custom.OrderDetailsDAO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailsDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;



public class OrderBOImpl implements OrderBO {

    OrderDAO ordersDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DAO);

    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM_DAO);

    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS_DAO);


    @Override
    public String gnerateNewOrderID(Connection connection) throws SQLException {
        String lastOrderId = ordersDAO.generateNextId(connection);
        if (lastOrderId != null){
            String prefix = lastOrderId.substring(0, 1);
            int number = Integer.parseInt(lastOrderId.substring(1));
            number++;
            String formattedNumber = String.format("%03d", number);
            return prefix + formattedNumber;
        }
        return"O001";
    }

    @Override
    public boolean purchseOrder(OrderDTO order, Connection connection) throws SQLException {
        connection.setAutoCommit(false);

        Order orders = new Order(order.getOid(),order.getDate(),order.getCustomerID());

        boolean saveOrder = ordersDAO.saveOrder(orders,connection);
        if (!saveOrder){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailsDTO orderDetail : order.getOrderDetails()) {
            boolean orderDetailSaved = orderDetailsDAO.save(connection, new OrderDetails(
                    orderDetail.getOid(),
                    orderDetail.getCode(),
                    orderDetail.getQty(),
                    orderDetail.getPayment()));
            if (!orderDetailSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Item item = itemDAO.search(connection, orderDetail.getCode());

            item.setQuantity((item.getQuantity()) - (orderDetail.getQty()));

            boolean isUpdated = itemDAO.update(connection, item);

            if (!isUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);

        return true;
    }
}
