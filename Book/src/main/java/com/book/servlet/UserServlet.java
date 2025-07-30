package com.book.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import com.book.dao.UserDAO;
import com.book.model.User;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/protected/user")// url pattren
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private UserDAO userDAO = new UserDAO();
	// private ObjectMapper objectMapper = new ObjectMapper();

	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	 	
	 	setCORSHeaders(response);
	 	
	 	
	     response.setContentType("application/json");
	     
	     String userId=request.getParameter("id");
	     if(userId==null || userId.isEmpty()) 
	     {
	    	 PrintWriter out = response.getWriter();
		     
		     List<User> users = userDAO.getUsers();
		     try {
		     	Gson gson=new Gson();
		     	String json=gson.toJson(users);
		         //String json = objectMapper.writeValueAsString(users);
		         out.print(json);
		         out.flush();
		     } catch (Exception e) {
		         response.setStatus(500);
		         out.print("Error processing JSON");
		         out.flush();
		     }
 
	     }
	     else {
	    	 UserDAO userDAO=new UserDAO();
	    	 int id=Integer.parseInt(userId);
	    	 User user =userDAO.getUser(id);
	    	 PrintWriter out = response.getWriter();
	    	 Gson gson=new Gson();
		     String json=gson.toJson(user);
	    	 out.print(json);
	     }
	}
	 @Override
	 protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	     response.setStatus(HttpServletResponse.SC_OK);
	 }


	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	 	setCORSHeaders(response);
	 	
	 	
	     response.setContentType("application/json");
	     PrintWriter out = response.getWriter();
	     try {
	     	Gson gson=new Gson();
	     	User user=gson.fromJson(new InputStreamReader(request.getInputStream()), User.class);
	        // User user = objectMapper.readValue(request.getInputStream(), User.class);
	         userDAO.addUser(user);
	         response.setStatus(201);
	         out.print("User added successfully");
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
	 	setCORSHeaders(response);
	     response.setContentType("application/json");
	     PrintWriter out = response.getWriter();
	     try {
	     	Gson gson=new Gson();
	     	User user=gson.fromJson(new InputStreamReader(request.getInputStream()), User.class);
	         //User user = objectMapper.readValue(request.getInputStream(), User.class);
	         userDAO.updateUser(user);
	         response.setStatus(200);
	         out.print("User updated successfully");
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
	 	setCORSHeaders(response);
	     int id = Integer.parseInt(request.getParameter("id"));
	     userDAO.deleteUser(id);
	     response.setStatus(200);
	 }
	 private void setCORSHeaders(HttpServletResponse response) {
	     response.setHeader("Access-Control-Allow-Origin", "*"); // Specify exact origin
	     response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
	     response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	     response.setHeader("Access-Control-Allow-Credentials", "true");  // Allow credentials (cookies)
	}

}
