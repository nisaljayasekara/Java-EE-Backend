package com.example.demo.api;

import com.example.demo.bo.BOFactory;
import com.example.demo.bo.custom.OrderBO;
import com.example.demo.dto.OrderDTO;
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

@WebServlet(urlPatterns = "/order",loadOnStartup = 2)
public class OrderServlet extends HttpServlet {

    OrderBO orderBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_BO);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try(var write = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            OrderDTO order = jsonb.fromJson(req.getReader(), OrderDTO.class);

            boolean isSaved = orderBO.purchseOrder(order, connection);
            if (isSaved){
                write.write("orderSaved");
            }else {
                write.write("not saved");
            }
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();

            resp.setContentType("application/json");
            jsonb.toJson(orderBO.gnerateNewOrderID(connection),writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
