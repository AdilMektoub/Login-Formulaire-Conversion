package com.mode.profil;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/profil")
public class Profil extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("txtNom");
		String prenom = request.getParameter("txtPrenom");
		String email =  request.getParameter("txtEmail");
		String adresse = request.getParameter("txtAdresse");
		String password = request.getParameter( "txtPassword" );

		if ( nom == null ) nom = "";
		if ( prenom == null ) prenom = "";
		if ( email == null ) email = "";
		if ( adresse == null ) adresse = "";
		if ( password == null ) password = "";

		HttpSession session = request.getSession( true );

		session.setAttribute( "nom", nom );
		session.setAttribute( "prenom", prenom );
		session.setAttribute( "email", email );
		session.setAttribute( "adresse", adresse );
		session.setAttribute( "password", password );

		request.getRequestDispatcher( "/Bienvenue.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nom = request.getParameter("txtNom");
		String prenom = request.getParameter("txtPrenom");
		String email =  request.getParameter("txtEmail");
		String adresse = request.getParameter("txtAdresse");
		String password = request.getParameter( "txtPassword" );

		HttpSession session = request.getSession( true );

		session.setAttribute( "nom", nom );
		session.setAttribute( "prenom", prenom );
		session.setAttribute( "email", email );
		session.setAttribute( "adresse", adresse );
		session.setAttribute( "password", password );
		System.out.println( "in the doPost" );
		
		if ( email.contains("@gmail.com") && password.length() >= 8 ) {
			session.setAttribute( "isConnected", true );
			request.getRequestDispatcher( "/BonneConnexion.jsp" ).forward( request, response );
		} else {
			session.setAttribute( "isConnected", false );
			request.getRequestDispatcher( "/Bienvenue.jsp" ).forward( request, response );
		}
	}
}