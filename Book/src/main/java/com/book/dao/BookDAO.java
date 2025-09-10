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
//	public List<Book> getBooks(int page, int limit) {
//        List<Book> books = new ArrayList<>();
//        String query = "SELECT * FROM books LIMIT ? OFFSET ?";
//        try {
//            Connection connection = DBConnection.getConnection();
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, limit);
//            statement.setInt(2, (page - 1) * limit);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    Book book = new Book();
//                    book.setId(resultSet.getInt("id"));
//                    book.setTitle(resultSet.getString("title"));
//                    book.setAuthor(resultSet.getString("author"));
//                    book.setPrice(resultSet.getDouble("price"));
//                    book.setIsbn(resultSet.getString("isbn"));
//                    book.setPublisher(resultSet.getString("publisher"));
//                    book.setPublicationDate(resultSet.getString("publicationDate"));
//                    books.add(book);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }
//
//    public Map<String, Object> getPaginatedBooks(int page, int limit) {
//        Map<String, Object> resultMap = new HashMap<>();
//        List<Book> books = getBooks(page, limit);
//        int totalCount = getBooksCount();
//        resultMap.put("books", books);
//        resultMap.put("totalCount", totalCount);
//        resultMap.put("page", page);
//        resultMap.put("limit", limit);
//        return resultMap;
//    }
//
//    public int getBooksCount() {
//        String query = "SELECT COUNT(*) FROM books";
//        try {
//            Connection connection = DBConnection.getConnection();
//            Statement statement = connection.createStatement();
//            try (ResultSet resultSet = statement.executeQuery(query)) {
//                if (resultSet.next()) {
//                    return resultSet.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

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
