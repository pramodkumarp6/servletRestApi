package com.pramod.model;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	 public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        if (request.getMethod().equalsIgnoreCase("PATCH")){
	           doPatch(request, response);
	        } else {
	            super.service(request, response);
	        }
	    }
	     
	    public abstract void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	 
	}
	  
	
