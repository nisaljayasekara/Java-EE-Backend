package com.example.demo.dao;

import com.example.demo.dao.custom.impl.CustomerDAOImpl;
import com.example.demo.dao.custom.impl.ItemDAOImpl;
import com.example.demo.dao.custom.impl.OrderDAOImpl;
import com.example.demo.dao.custom.impl.OrderDetailsDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER_DAO,
        ITEM_DAO,
        ORDER_DAO,

        ORDERDETAILS_DAO
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER_DAO:
                return (T) new CustomerDAOImpl();
            case ITEM_DAO:
                return (T) new ItemDAOImpl();
            case ORDER_DAO:
                return (T) new OrderDAOImpl();
            case ORDERDETAILS_DAO:
                return (T) new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }
}