package com.example.demo.dao.custom;

import com.example.demo.dao.CrudDAO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO {
    String saveItem(ItemDTO item , Connection connection) throws SQLException;

    boolean deleteItem(String code, Connection connection) throws Exception;

    boolean updateItem(String code, ItemDTO item, Connection connection) throws SQLException;

    List<Item> getAllItem(Connection connection) throws Exception;


    Item search(Connection connection, String code) throws SQLException;

    boolean update(Connection connection, Item item) throws SQLException;
}
