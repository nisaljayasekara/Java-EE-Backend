package com.example.demo.bo.custom;

import com.example.demo.bo.SuperBO;
import com.example.demo.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemtBO extends SuperBO {

    String saveItem(ItemDTO item, Connection connection) throws Exception;

    boolean deleteItem(String code, Connection connection) throws Exception;

    boolean updateItem(String code, ItemDTO item, Connection connection) throws SQLException;

    List<ItemDTO> getAllItem(Connection connection) throws Exception;
}
