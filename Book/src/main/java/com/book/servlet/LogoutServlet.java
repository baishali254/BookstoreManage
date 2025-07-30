package com.book.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		 setCORSHeaders(response); 
        request.getSession().invalidate();
        response.setStatus(200);
    }
	  @Override
	    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      setCORSHeaders(response); 
	        response.setStatus(HttpServletResponse.SC_OK);
	    }
	  private void setCORSHeaders(HttpServletResponse response) {
	        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");  // Frontend origin
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	        response.setHeader("Access-Control-Allow-Credentials", "true");
	    }

}