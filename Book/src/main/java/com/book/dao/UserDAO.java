package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.book.model.User;

public class UserDAO {
	//To get all the users
		public List<User> getUsers() {
		        List<User> users = new ArrayList<>();
		        String query = "SELECT * FROM users";
		        try {Connection connection = DBConnection.getConnection();
		             Statement statement =  connection.createStatement();
		             ResultSet resultSet = (statement).executeQuery(query); 

		            while (resultSet.next()) {
		                User user = new User();
		                user.setId(resultSet.getInt("id"));
		                user.setUsername(resultSet.getString("username"));
		                user.setEmail(resultSet.getString("email"));
		                user.setPassword(resultSet.getString("password"));
		                user.setRole(resultSet.getString("role"));
		                user.setFirstName(resultSet.getString("firstName"));
		                user.setLastName(resultSet.getString("lastName"));
		                user.setAddress(resultSet.getString("address"));
		                user.setPhone(resultSet.getString("phone"));
		                users.add(user);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return users;
		    }

			//get user by Id
		
		    public User getUser(int id) {
		        User user = null;
		        String query = "SELECT * FROM users WHERE id = ?";//dynamic query
		        try {Connection connection = DBConnection.getConnection();
		             PreparedStatement statement = connection.prepareStatement(query);

		            statement.setInt(1, id);
		            try (ResultSet resultSet = statement.executeQuery()) {
		                if (resultSet.next()) {
		                    user = new User();
		                    user.setId(resultSet.getInt("id"));
		                    user.setUsername(resultSet.getString("username"));
		                    user.setEmail(resultSet.getString("email"));
		                    user.setPassword(resultSet.getString("password"));
		                    user.setRole(resultSet.getString("role"));//ADMIN, USER, GUEST
		                    user.setFirstName(resultSet.getString("firstName"));
		                    user.setLastName(resultSet.getString("lastName"));
		                    user.setAddress(resultSet.getString("address"));
		                    user.setPhone(resultSet.getString("phone"));
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return user;
		    }
		    
		    //get user by username and password
		    public User getUserByUsernameAndPassword(String username, String password) {
		        User user = null;
		        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		        try {Connection connection = DBConnection.getConnection();
		             PreparedStatement statement = connection.prepareStatement(query);

		            statement.setString(1, username);
		            statement.setString(2, password);
		            try (ResultSet resultSet = statement.executeQuery()) {
		                if (resultSet.next()) {
		                    user = new User();
		                    user.setId(resultSet.getInt("id"));
		                    user.setUsername(resultSet.getString("username"));
		                    user.setEmail(resultSet.getString("email"));
		                    user.setPassword(resultSet.getString("password"));
		                    user.setRole(resultSet.getString("role"));
		                    user.setFirstName(resultSet.getString("firstName"));
		                    user.setLastName(resultSet.getString("lastName"));
		                    user.setAddress(resultSet.getString("address"));
		                    user.setPhone(resultSet.getString("phone"));
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return user;
		    }
		    //Add a user
		    public void addUser(User user) {
		        String query = "INSERT INTO users (username, email, password, role, firstName, lastName, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		        try {Connection connection = DBConnection.getConnection();
		             PreparedStatement statement = connection.prepareStatement(query);

		            statement.setString(1, user.getUsername());
		            statement.setString(2, user.getEmail());
		            statement.setString(3, user.getPassword());
		            statement.setString(4, user.getRole());
		            statement.setString(5, user.getFirstName());
		            statement.setString(6, user.getLastName());
		            statement.setString(7, user.getAddress());
		            statement.setString(8, user.getPhone());
		            statement.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    //Update User
		    public void updateUser(User user) {
		        String query = "UPDATE users SET username = ?, email = ?, password = ?, role = ?, firstName = ?, lastName = ?, address = ?, phone = ? WHERE id = ?";
		        try {Connection connection = DBConnection.getConnection();
		             PreparedStatement statement = connection.prepareStatement(query);

		            statement.setString(1, user.getUsername());
		            statement.setString(2, user.getEmail());
		            statement.setString(3, user.getPassword());
		            statement.setString(4, user.getRole());
		            statement.setString(5, user.getFirstName());
		            statement.setString(6, user.getLastName());
		            statement.setString(7, user.getAddress());
		            statement.setString(8, user.getPhone());
		            statement.setInt(9, user.getId());
		            statement.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		    //to delete a user
		    public void deleteUser(int id) {
		        String query = "DELETE FROM users WHERE id = ?";
		        try {Connection connection = DBConnection.getConnection();
		             PreparedStatement statement = connection.prepareStatement(query);

		            statement.setInt(1, id);
		            statement.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
}
