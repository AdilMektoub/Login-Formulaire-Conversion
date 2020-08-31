package com.control.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login") // dans le jsp form action 
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String email = request.getParameter( "txtEmail" );//input text dans bienvenue.jsp
		String password = request.getParameter( "txtPassword" );
		if (email == null) email = "";
		if ( password == null ) password = "";
		HttpSession session = request.getSession( true );
		session.setAttribute( "email", email );
		session.setAttribute( "password", password );
		request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter( "txtEmail" );
		String password = request.getParameter( "txtPassword" ); //input text dans bienvenue.jsp
		String errorMail = ""; // si l'email est pas bon
		String errorPassword = ""; // si le password est mauvais
		HttpSession session = request.getSession( true );
		session.setAttribute( "email", email );
		session.setAttribute( "password", password );
		session.setAttribute("errorMail", errorMail);
		session.setAttribute("errorAge", errorPassword);
		request.setAttribute( "email", email );
		request.setAttribute( "password", password );
		
		System.out.println( "in the doPost" );
		String[] mails = {"anis@pop.fr", "imen@pop.com","inga@yahoo.fr"};
		boolean mailExist = false; 
		for (String s : mails) {
			if (s.equals(mails)) mailExist = true; 	
		}
//			if ( (email.equals("anis@pop.fr") ||email.equals("imen@pop.com") || email.equals("inga@yahoo.fr") )&& password.equals( "admin" ) )
		 if ( mailExist && password.equals("admin")) {
			session.setAttribute( "isConnected", true );
			System.out.println(email + password);
			request.getRequestDispatcher( "/connected.jsp" ).forward( request, response );
		} 
		else {
		
		if (!email.equals("anis@pop.fr") || !email.equals("imen@pop.com") || !email.equals("inga@yahoo.fr")) {
			errorMail = "L'adresse mail n'est pas bonne !";
			System.out.println(errorMail);
			session.setAttribute("errorMail", errorMail);
		}
		if (!password.equals("admin")) {
			errorMail = "Le Password n'est pas bon !";
			System.out.println(errorMail);
			session.setAttribute("errorMail", errorMail);
		}
		doGet(request, response);
		}
	}
}
