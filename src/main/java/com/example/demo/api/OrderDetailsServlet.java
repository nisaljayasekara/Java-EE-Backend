package com.example.demo.api;

import com.example.demo.bo.BOFactory;
import com.example.demo.bo.custom.OrderDetailsBO;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/orderDetails",loadOnStartup = 2)
public class OrderDetailsServlet extends HttpServlet {
    OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERDETAILS_BO);

    Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();

        } catch (SQLException | NamingException e){

            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();

            resp.setContentType("application/json");
            jsonb.toJson(orderDetailsBO.getAllOrderDetails(connection),writer);
        }catch (Exception e){
            e.printStackTrace();
                }
        }
}
