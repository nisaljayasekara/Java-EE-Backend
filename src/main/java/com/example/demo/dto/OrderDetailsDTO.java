package com.example.demo.dto;

import com.example.demo.entity.OrderDetails;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailsDTO {

    private String oid;
    private String code;
    private int qty;
    private int payment;

}
