package com.book.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.book.dao.UserDAO;
import com.book.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();
    private Gson gson = new Gson();

    private void setCORSHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");  // Frontend origin
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCORSHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCORSHeaders(response);
        response.setContentType("application/json");

        try {
            User user = gson.fromJson(new InputStreamReader(request.getInputStream()), User.class);
            User existingUser = userDAO.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());

            if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", existingUser);
                System.out.println("Session ID: " + session.getId());
                System.out.println("User stored in session: " + existingUser.getUsername());

                // Set SameSite=None and Secure cookie properties for cross-origin requests
//                Cookie jsessionCookie = new Cookie("JSESSIONID", session.getId());
//                jsessionCookie.setPath("/"); // Make the cookie available to the entire application
//                jsessionCookie.setHttpOnly(true); // Prevent JavaScript access to the session cookie
//                jsessionCookie.setSecure(true); // Make the cookie secure (HTTPS only)
//                jsessionCookie.setSameSite("None"); // Allow cross-site cookie sending
//                response.addCookie(jsessionCookie); // Add the cookie to the response
                
                // Manually set the SameSite=None and Secure cookie attributes
                String jsessionId = session.getId();
                String cookieHeader = "JSESSIONID=" + jsessionId + "; Path=/; HttpOnly; Secure; SameSite=None";
                response.setHeader("Set-Cookie", cookieHeader);
                // Create a JSON object that includes the JSESSIONID and user data
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("jsessionId", session.getId());
                jsonObject.add("user", gson.toJsonTree(existingUser));

                PrintWriter out = response.getWriter();
                out.print(jsonObject.toString());
                out.flush();
            } else {
                response.setStatus(401);
                PrintWriter out = response.getWriter();
                out.print("Invalid username or password");
                out.flush();
            }
        } catch (Exception e) {
            response.setStatus(400);
            PrintWriter out = response.getWriter();
            out.print("Invalid JSON request");
            out.flush();
        }
    }
}