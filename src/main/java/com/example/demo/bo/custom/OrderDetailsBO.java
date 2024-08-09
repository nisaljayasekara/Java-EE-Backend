package com.example.demo.bo.custom;

import com.example.demo.dto.OrderDetailsDTO;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailsBO {
    List<OrderDetailsDTO> getAllOrderDetails(Connection connection) throws Exception;
}
