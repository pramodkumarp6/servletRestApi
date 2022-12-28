package com.pramod.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pramod.model.DbOpertion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ObjectMapper om = new ObjectMapper();
	DbOpertion db = new DbOpertion();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DbOpertion db = new DbOpertion();
		response.setHeader("Content-Type", "application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);
		//response.setHeader("Access-Control-Allow-Methods", "POST");

		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();

		String line;
		try {

			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}

			System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
