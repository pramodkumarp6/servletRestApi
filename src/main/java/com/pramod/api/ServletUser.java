package com.pramod.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.pramod.model.DbOpertion;
import com.pramod.model.MyServlet;
import com.pramod.model.Users;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/users/")
public class ServletUser  extends MyServlet {
	private static final long serialVersionUID = 1L;
	ObjectMapper om = new ObjectMapper();
	DbOpertion db = new DbOpertion();

	
	
	
	
	  public void init(ServletConfig config) throws ServletException {
	        super.init(config);
	    }
	 
	
	
	public void doPatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setHeader("Content-Type", "application/json");
		response.setStatus(200);
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
        String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
			Users users = om.readValue(sb.toString(), Users.class);
			
			//out.print(om.writeValueAsString(users));
			out.print(sb.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			out.write("{\"error\":\"" + "Unrecognized field" + "\"}");
		}


	}
	
	
	
	

}
