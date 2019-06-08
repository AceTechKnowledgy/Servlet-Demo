package com.ace.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String myMessage;
	
	//When this servlet receives the request, this servlet will be loaded by the container and this method will be called once.
	@Override
	public void init() throws ServletException {
		myMessage = "Hello World";
	}
	
	//When the request type is "GET", then this method will be called and return a html page with <h1> tag
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Setting the response type as html page
		resp.setContentType("text/html");
		
		//Creating a session object using HttpSession
		HttpSession session = req.getSession();
		session.setAttribute("userId", session.getId());
		System.out.println("Creation of session: "+session.getId());
		
		//Creating a session object using Cookie
		Cookie cookie = new Cookie("cookieName", session.getId());
		resp.addCookie(cookie);
		
		PrintWriter writer = resp.getWriter();
		writer.println("<h1>"+ myMessage + "</h1>");
	}

	//Once the user filled the Username and password, then click the submit button, this method will be called.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Getting the input data from the form
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		
		//Getting session object using HttpSession
		HttpSession session = req.getSession(false);
		String userid = (String) session.getAttribute("userId");
		System.out.println("Getting session: "+userid);
		
		//Getting session object using Cookie
		Cookie[] cookies = req.getCookies();
		for(Cookie c : cookies) {
			System.out.println("Cookie: "+c.toString());
		}
		
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println("<h2>" + userName + "</h2>");
		writer.println("<h2>" + password + "</h2>");
		writer.close();
	}
}
