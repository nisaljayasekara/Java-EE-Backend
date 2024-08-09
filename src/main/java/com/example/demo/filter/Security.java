//package com.example.demo.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpFilter;
//
//import java.io.IOException;
//
//public class Security extends HttpFilter {
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        System.out.println("Security Filter");
//        chain.doFilter(req,res);
//    }
//}
