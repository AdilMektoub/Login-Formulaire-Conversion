package com.mode.mailValidation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Mail
 */
@WebServlet("/mail")
public class Mail extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter( "txtLogin" );

		if ( login == null ) login = "";

		HttpSession session = request.getSession( true );

		session.setAttribute( "login", login );
		request.getRequestDispatcher( "/mail.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String login = request.getParameter( "txtLogin" ); //
		String client = null;
		HttpSession session = request.getSession( true );
		session.setAttribute( "login", login );

		System.out.println( "in the doPost" );
		if ( login.contains("@gmail.com") ) {
			session.setAttribute( "isConnected", true );
			client = "Google";
			session.setAttribute( "client", client );
			request.getRequestDispatcher( "/ConnectedMail.jsp" ).forward( request, response );
		}
		else if ( login.contains("@hotmail.com") ||  login.contains("@outlook.com") ) {
			session.setAttribute( "isConnected", true );
			client = "Microsoft";
			session.setAttribute( "client", client );
			request.getRequestDispatcher( "/ConnectedMail.jsp" ).forward( request, response );
		}
		else {
			session.setAttribute( "isConnected", false );
			request.getRequestDispatcher( "/mail.jsp" ).forward( request, response ); // doGet(request, response );			
		}	
	}
}
