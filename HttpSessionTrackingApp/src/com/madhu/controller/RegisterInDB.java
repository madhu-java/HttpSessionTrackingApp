package com.madhu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.madhu.JdbcUtil.JdbcUtil;

@WebServlet("/storeindb")
public class RegisterInDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PreparedStatement prepareStatement = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("storing in db");
		HttpSession session = request.getSession(false);
		try {
			Connection connection = JdbcUtil.getJdbcConnection();
			String sqlInsertQuery = "insert into user(`name`,`age`,`qualification`,`designation`,`mobile`,`email`) values(?,?,?,?,?,?)";

			if (connection != null) {
				prepareStatement = connection.prepareStatement(sqlInsertQuery);
			}
			if (prepareStatement != null) {
				prepareStatement.setString(1, (String) session.getAttribute("sname"));
				prepareStatement.setInt(2,  Integer.valueOf((String) session.getAttribute("sage")));
				prepareStatement.setString(3, (String) session.getAttribute("squal"));
				prepareStatement.setString(4, (String) session.getAttribute("sdesg"));
				prepareStatement.setInt(5, Integer.valueOf((String) session.getAttribute("smobile")));
				prepareStatement.setString(6, (String) session.getAttribute("semail"));
			}
			if (prepareStatement != null) {
				int rowsAffected = prepareStatement.executeUpdate();
				RequestDispatcher requestDispatcher = null;

				if (rowsAffected == 1) {
					requestDispatcher = request.getRequestDispatcher("./success.html");
					requestDispatcher.forward(request, response);
				}else {
					requestDispatcher = request.getRequestDispatcher("./failure.html");
					requestDispatcher.forward(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
