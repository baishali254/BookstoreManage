package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.book.model.Order;

public class OrderDAO {
	public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try {Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userId"));
                order.setBookId(resultSet.getInt("bookId"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderDate(resultSet.getString("orderDate"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrder(int id) {
        Order order = null;
        String query = "SELECT * FROM orders WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setUserId(resultSet.getInt("userId"));
                    order.setBookId(resultSet.getInt("bookId"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    order.setTotal(resultSet.getDouble("total"));
                    order.setOrderDate(resultSet.getString("orderDate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public void addOrder(Order order) {
        String query = "INSERT INTO orders (userId, bookId, quantity, total, orderDate) VALUES (?, ?, ?, ?, ?)";
        try {Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getBookId());
            statement.setInt(3, order.getQuantity());
            statement.setDouble(4, order.getTotal());
            statement.setString(5, order.getOrderDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order) {
        String query = "UPDATE orders SET userId = ?, bookId = ?, quantity = ?, total = ?, orderDate = ? WHERE id = ?";
        try {Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getBookId());
            statement.setInt(3, order.getQuantity());
            statement.setDouble(4, order.getTotal());
            statement.setString(5, order.getOrderDate());
            statement.setInt(6, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        String query = "DELETE FROM orders WHERE id = ?";
        try {Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE userId = ?";
        try {Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setUserId(resultSet.getInt("userId"));
                    order.setBookId(resultSet.getInt("bookId"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    order.setTotal(resultSet.getDouble("total"));
                    order.setOrderDate(resultSet.getString("orderDate"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
