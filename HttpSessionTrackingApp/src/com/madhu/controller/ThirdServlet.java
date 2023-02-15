package com.madhu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/third")
public class ThirdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String smobile = request.getParameter("smobile");
     String semail = request.getParameter("semail");
     
     HttpSession session = request.getSession(false);
     session.setAttribute("smobile", smobile);
     session.setAttribute("semail", semail);
     
     System.out.println(request.getRequestedSessionId());
     response.setContentType("text/html");
     PrintWriter writer = response.getWriter();
     writer.println("<html><head><title>Output</title></head>");
     writer.println("<body bgcolor='lightgreen'>" );
     writer.println("<h1 style='color:red;text-align:center;'>Registration form</h1>");
     writer.println("<table border='1'>");
     writer.println("<center>");
     writer.println("<tr><th>Name</th><th>Value</th></tr>");
     
     Enumeration<String> attributeNames = session.getAttributeNames();
     while (attributeNames.hasMoreElements()) {
		String attributeName = (String) attributeNames.nextElement();
		Object attributeValue = session.getAttribute(attributeName);
		 writer.println("<tr><td>"+attributeName+"</td><td>"+attributeValue+"</td></tr>");
		 
	}
     
     writer.println("<form method='post' action='./storeindb'>");
     writer.println("<tr><td><input type='submit' value='Register'/></td></tr>");
     writer.println("</form>");
     writer.println("</center>");
     writer.println("</table></body</html>");
     writer.close();
     
     
     
	}

}
