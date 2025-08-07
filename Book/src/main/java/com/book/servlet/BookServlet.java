package com.book.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.book.dao.BookDAO;
import com.book.model.Book;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/protected/book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO = new BookDAO();
   
	private void setCorsHeaders(HttpServletResponse response) {
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	}

	private void handlePreFlightRequest(HttpServletRequest request, HttpServletResponse response) {
	    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	        response.setStatus(HttpServletResponse.SC_OK);
	    }
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    setCorsHeaders(response);
	    handlePreFlightRequest(request, response);
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();

	    String idStr = request.getParameter("id");
	    int id = 0;
	    if (idStr != null && !idStr.isEmpty()) {
	        try {
	            id = Integer.parseInt(idStr);
	            // Return book details for specific ID
	            Book book = bookDAO.getBook(id);
	            Gson gson = new Gson();
	            String json = gson.toJson(book);
	            out.print(json);
	            out.flush();
	        } catch (NumberFormatException e) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book ID");
	            return;
	        }
	    } else {
	        int page = 1;
	        int limit = 10;
	        String pageStr = request.getParameter("page");
	        String limitStr = request.getParameter("limit");
	        if (pageStr != null && !pageStr.isEmpty()) {
	            page = Integer.parseInt(pageStr);
	        }
	        if (limitStr != null && !limitStr.isEmpty()) {
	            limit = Integer.parseInt(limitStr);
	        }
	        try {
	            List<Book> books = bookDAO.getBooks(page, limit);
	            int totalCount = bookDAO.getBooksCount();
	            Map<String, Object> resultMap = new HashMap<>();
	            resultMap.put("books", books);
	            resultMap.put("totalCount", totalCount);
	            resultMap.put("page", page);
	            resultMap.put("limit", limit);
	            Gson gson = new Gson();
	            String json = gson.toJson(resultMap);
	            out.print(json);
	            out.flush();
	        } catch (Exception e) {
	            response.setStatus(500);
	            out.print("Error processing JSON");
	            out.flush();
	        }
	    }
	}
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  setCorsHeaders(response);
    	    handlePreFlightRequest(request, response);
        response.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  setCorsHeaders(response);
    	    handlePreFlightRequest(request, response);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
        	Gson gson=new Gson();
        	Book book= gson.fromJson(new InputStreamReader(request.getInputStream()), Book.class);
            //Book book = objectMapper.readValue(request.getInputStream(), Book.class);
            bookDAO.addBook(book);
            response.setStatus(201);
            response.setContentType("application/json");  // Send as JSON
            response.getWriter().write("{\"message\": \"Book added successfully!\"}");
            out.flush();
        } catch (JsonMappingException e) {
            response.setStatus(400);
            out.print("Invalid JSON request");
            out.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  setCorsHeaders(response);
    	    handlePreFlightRequest(request, response);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
        	Gson gson=new Gson();
        	Book book= gson.fromJson(new InputStreamReader(request.getInputStream()), Book.class);
            //Book book = objectMapper.readValue(request.getInputStream(), Book.class);
            bookDAO.updateBook(book);
            response.setStatus(200);
            out.print("Book updated successfully");
            out.flush();
        } catch (JsonMappingException e) {
            response.setStatus(400);
            out.print("Invalid JSON request");
            out.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Enable CORS
       
    	  setCorsHeaders(response);
    	    handlePreFlightRequest(request, response);
        // Check for pre-flight request (OPTIONS)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // Proceed with your regular DELETE logic
        int id = Integer.parseInt(request.getParameter("id"));
        bookDAO.deleteBook(id);
        
        response.setStatus(HttpServletResponse.SC_OK); // Set status to 200 OK
    }
}
