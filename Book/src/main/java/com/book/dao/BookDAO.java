package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.book.model.Book;

public class BookDAO {

    public Book getBook(int id) {
        Book book = null;
        String query = "SELECT * FROM books WHERE id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPrice(resultSet.getDouble("price"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setPublisher(resultSet.getString("publisher"));
                    book.setPublicationDate(resultSet.getString("publicationDate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, price, isbn, publisher, publicationDate) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3, book.getPrice());
            statement.setString(4, book.getIsbn());
            statement.setString(5, book.getPublisher());
            statement.setString(6, book.getPublicationDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, price = ?, isbn = ?, publisher = ?, publicationDate = ? WHERE id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDouble(3, book.getPrice());
            statement.setString(4, book.getIsbn());
            statement.setString(5, book.getPublisher());
            statement.setString(6, book.getPublicationDate());
            statement.setInt(7, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String query = "DELETE FROM books WHERE id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
