package com.book.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import com.book.dao.OrderDAO;
import com.book.model.Order;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/protected/order")
public class OrderServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = new OrderDAO();
   // private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        List<Order> orders = orderDAO.getOrders();
        try {
        	Gson gson=new Gson();
        	String json=gson.toJson(orders);
            //String json = objectMapper.writeValueAsString(orders);
            out.print(json);
            out.flush();
        } catch (Exception e) {
            response.setStatus(500);
            out.print("Error processing JSON");
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
        	Gson gson=new Gson();
        	Order order = gson.fromJson(new InputStreamReader(request.getInputStream()), Order.class);
           // Order order = objectMapper.readValue(request.getInputStream(), Order.class);
            orderDAO.addOrder(order);
            response.setStatus(201);
            out.print("Order added successfully");
            out.flush();
        } catch (Exception e) {
            response.setStatus(400);
            out.print("Invalid JSON request");
            out.flush();
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
        	Gson gson=new Gson();
        	Order order = gson.fromJson(new InputStreamReader(request.getInputStream()), Order.class);
           // Order order = objectMapper.readValue(request.getInputStream(), Order.class);
            orderDAO.updateOrder(order);
            response.setStatus(200);
            out.print("Order updated successfully");
            out.flush();
        } catch (Exception e) {
            response.setStatus(400);
            out.print("Invalid JSON request");
            out.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderDAO.deleteOrder(id);
        response.setStatus(200);
    }

}
