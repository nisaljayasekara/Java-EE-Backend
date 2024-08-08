package com.example.demo.dao.custom.impl;

import com.example.demo.dao.custom.ItemDAO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    public static String SAVE_ITEM = "INSERT INTO items (code,name,price,quantity) VALUES(?,?,?,?)";

    public static String DELETE_ITEM = "DELETE FROM items where code=?";

    public static String UPDATE_ITEM = "UPDATE items SET name=?, price=?, quantity=? WHERE code=?";

    public static String GET_ITEM = "SELECT * FROM items";
    public String saveItem(ItemDTO item , Connection connection) throws SQLException {
        try{
            var sc = connection.prepareStatement(SAVE_ITEM);
            sc.setString(1,item.getCode());
            sc.setString(2,item.getName());
            sc.setInt(3,item.getPrice());
            sc.setInt(4,item.getQuantity());

            if(sc.executeUpdate() != 0){
                return "Product Save Successfully";
            }else {
                return "Failed to Save Product";
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public boolean deleteItem(String code, Connection connection) throws SQLException {
        var sc = connection.prepareStatement(DELETE_ITEM);
        sc.setString(1,code);
        return sc.executeUpdate() !=0;
    }

    public boolean updateItem(String code, ItemDTO item, Connection connection) throws SQLException {
        try{
            var sc = connection.prepareStatement(UPDATE_ITEM);
            sc.setString(1, item.getCode());
            sc.setString(2,item.getName());
            sc.setInt(3,item.getPrice());
            sc.setInt(4,item.getQuantity());
            return sc.executeUpdate() !=0;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public List<Item> getAllItem(Connection connection) throws SQLException {
        try {
            ItemDTO itemDTO = new ItemDTO();
            var sc = connection.prepareStatement(GET_ITEM);
            ResultSet rst = sc.executeQuery();
            ArrayList<Item> products = new ArrayList<>();

            while (rst.next()){
                products.add(new Item(
                        rst.getString("code"),
                        rst.getString("name"),
                        rst.getInt("price"),
                        rst.getInt("quantity")));
            }
            return products;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
