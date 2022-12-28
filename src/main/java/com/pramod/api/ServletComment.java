package com.pramod.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pramod.model.DbOpertion;
import com.pramod.model.Users;
import com.pramod.utils.RequestMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@WebServlet(name = "ServletUser", urlPatterns = "/api/comment/*")

public class ServletComment  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	ObjectMapper om = new ObjectMapper();
	DbOpertion db = new DbOpertion();
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DbOpertion db = new DbOpertion();

		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);
		
		try {
			if (request.getPathInfo() != null && request.getPathInfo().length() > 1) {
				List<String> paths = Arrays.asList(request.getPathInfo().substring(1).split("/"));
				if (paths.size() > 1) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

					out.write("{\"error\":\"" + "invalid Url path" + "\"}");

				} else {
					Users users = db.getfindById(Integer.parseInt(paths.get(0)));

					out.print(om.writeValueAsString(users));

				}
			} else {

				List<Users> users = db.getData();
				out.print(om.writeValueAsString(users));
				out.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}



