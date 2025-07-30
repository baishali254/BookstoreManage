package com.book.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
      private static final String url="jdbc:mysql://localhost:3306/book?serverTimezone=UTC";
      private static final String username="root";
      private static final String password="20040327";
      
      private static Connection connection;
      
      public static Connection getConnection() {
    	   
    	  if(connection==null)
    	  {
    		  try {
    			  Class.forName("com.mysql.cj.jdbc.Driver");
    			  connection=DriverManager.getConnection(url,username,password);
    			  
    			  System.out.println("connection is created successfully");
    			  
    		  }
    		  catch(Exception e) {
    			  
    			  System.out.println("not created because "+e);
    		  }
    	  }
    	  return connection;
      }
      
      public void closeConnection() {
    	  
    	  if(connection!=null) {
    		  try {
    			  connection.close();
    			  System.out.println("closed successfully..... ");
    		  }
    		  catch(Exception e){
    			  
    			  System.out.println("not closed beacuse "+e);
    		  }
    	  }
      }
      
      public static void main(String[] agrs)
      {
    	  getConnection();
      }
}
