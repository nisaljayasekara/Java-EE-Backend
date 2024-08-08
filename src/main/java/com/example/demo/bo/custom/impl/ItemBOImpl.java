package com.example.demo.bo.custom.impl;

import com.example.demo.bo.custom.ItemtBO;
import com.example.demo.dao.DAOFactory;
import com.example.demo.dao.custom.ItemDAO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemtBO {

    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM_DAO);

    public String saveItem(ItemDTO item, Connection connection) throws Exception {
        return itemDAO.saveItem(item,connection);
    }

    public boolean deleteItem(String code, Connection connection) throws Exception {
        return itemDAO.deleteItem(code,connection);
    }

    public boolean updateItem(String code, ItemDTO item, Connection connection) throws SQLException {
        return itemDAO.updateItem(code,item,connection);
    }

    public List<ItemDTO> getAllItem(Connection connection) throws Exception{
        List<Item> itemList = itemDAO.getAllItem(connection);
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : itemList){
            itemDTOS.add(new ItemDTO(
                 item.getCode(),
                 item.getName(),
                 item.getPrice(),
                 item.getQuantity()
            ));
        }
        return itemDTOS;
    }
}
