
package com.pramod.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pramod.model.DbOpertion;
import com.pramod.model.Users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", urlPatterns = "/api/users/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	ObjectMapper om = new ObjectMapper();
	DbOpertion db = new DbOpertion();

	public UserController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DbOpertion db = new DbOpertion();
		
		/*
		 * Users t = new Users();
		 * 
		 * List<Users> t= db.getData();
		 * 
		 * System.out.print(t);
		 * 
		 * 
		 */
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);
		Gson gson = new Gson();

		try {
			if (request.getPathInfo() != null && request.getPathInfo().length() > 1) {
				List<String> paths = Arrays.asList(request.getPathInfo().substring(1).split("/"));
				if (paths.size() > 1) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

					out.write("{\"error\":\"" + "invalid Url path" + "\"}");

				} else {
					Users users = db.getfindById(Integer.parseInt(paths.get(0)));

					out.print(gson.toJson(users));
					request.setAttribute("index", users); 
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

				}
			} else {

				List<Users> users = db.getData();
				out.print(gson.toJson(users));
				out.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//@RequestMap("/register")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(201);
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();

		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
			Users users = om.readValue(sb.toString(), Users.class);
			int status = db.save(users);
			if (status > 0) {
				pw.write("{\"message\":\"" + "Account Created Successfully" + "\"}");
			} else {
				pw.write("{\"error\":\"" + "Not Created Account " + "\"}");

			}

		} catch (Exception e) {
			pw.write("{\"error\":\"" + "Unrecognized field" + "\"}");
		}

		pw.flush();

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setStatus(200);
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();

		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}

			Users users = om.readValue(sb.toString(), Users.class);
			int status = db.update(users);
			if (status > 0) {
				pw.write("{\"message\":\"" + "Account Update Sucessfully" + "\"}");

			} else {
				pw.write("{\"error\":\"" + "Not Updated Account" + "\"}");
			}

		} catch (Exception e) {
			pw.write("{\"error\":\"" + "Unrecognized field" + "\"}");
		}
		// pw.print(gson.toJson(users));
		pw.flush();
	}

	

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setHeader("Content-Type", "application/json");

		try {
			if (request.getPathInfo() != null && request.getPathInfo().length() > 0) {
				List<String> paths = Arrays.asList(request.getPathInfo().substring(1).split("/"));
				System.out.println(paths);
				if (paths.size() > 1) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					out.write("{\"error\":\"" + "invalid Url path" + "\"}");
				} else {

					db.deleteById(Integer.parseInt(paths.get(0)));
					out.write("{\"message\":\"" + "Record Deleted Succesfully" + "\"}");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.write("{\"error\":\"" + e.getMessage() + "\"}");
		}

	}
}
