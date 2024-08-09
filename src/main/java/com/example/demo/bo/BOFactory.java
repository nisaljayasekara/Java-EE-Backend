package com.example.demo.bo;

import com.example.demo.bo.custom.impl.CustomerBOImpl;
import com.example.demo.bo.custom.impl.ItemBOImpl;
import com.example.demo.bo.custom.impl.OrderBOImpl;
import com.example.demo.bo.custom.impl.OrderDetailsBOImpl;
import com.example.demo.dao.custom.impl.OrderDetailsDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER_BO,
        ITEM_BO,

        ORDER_BO,
        ORDERDETAILS_BO

    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes) {
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            case ITEM_BO:
                return (T) new ItemBOImpl();
            case ORDER_BO:
                return (T) new OrderBOImpl();
            case ORDERDETAILS_BO:
                return (T) new OrderDetailsBOImpl();
            default:
                return null;
        }
        }
}
