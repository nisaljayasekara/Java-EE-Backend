package com.example.demo.bo.custom.impl;

import com.example.demo.bo.custom.OrderDetailsBO;
import com.example.demo.dao.DAOFactory;
import com.example.demo.dao.custom.OrderDetailsDAO;
import com.example.demo.dto.OrderDetailsDTO;
import com.example.demo.entity.OrderDetails;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {

    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS_DAO);


    public List<OrderDetailsDTO> getAllOrderDetails(Connection connection) throws Exception {
        List<OrderDetails> OrderDetailsList = orderDetailsDAO.getAllOrderDetails(connection);
        List<OrderDetailsDTO> OrderDetailsDTOS = new ArrayList<>();


        for (OrderDetails orderDetails : OrderDetailsList) {
            OrderDetailsDTOS.add(new OrderDetailsDTO(
                    orderDetails.getOid(),
                    orderDetails.getCode(),
                    orderDetails.getQty(),
                    orderDetails.getPayment()
            ));
        }
        return OrderDetailsDTOS;
    }
}
