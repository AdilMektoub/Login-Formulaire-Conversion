package com.control.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mode.login.String;

/**
 * Servlet implementation class bienvenue
 */
@WebServlet("/bienvenue")
public class bienvenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bienvenue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter( "txtNom" ); //input text dans bienvenue.jsp
		String age = request.getParameter( "txtAge" );
		String email = request.getParameter( "txtEmail" );
		if ( nom == null ) nom = "";
		if ( age == null ) age = "";
		if (email == null) email = "";
		HttpSession session = request.getSession( true );
		session.setAttribute( "nom", nom );
		session.setAttribute( "age", age );
		session.setAttribute( "email", email );
		request.getRequestDispatcher( "/profil.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter( "txtNom" ); //input text dans bienvenue.jsp
		String age = request.getParameter( "txtAge" );
		String email = request.getParameter( "txtEmail" ); 
		HttpSession session = request.getSession( true );
		session.setAttribute( "nom", nom );
		session.setAttribute( "age", age );
		session.setAttribute( "email", email );
		request.setAttribute( "nom", nom );
		request.setAttribute( "age", age );
		request.setAttribute( "email", email );
		System.out.println( "in the doPost" );
		
		int i=Integer.parseInt(age); // je CONVERTIE les String en entier age
		if ((i > 17 && i < 91) && (email.contains("@gmail.com") || email.contains( "@yahoo.fr" ))) { //l'age doit etre entre 18 et 90 ans
			session.setAttribute( "isConnected", true ); // tu te connecte
			request.getRequestDispatcher( "/bienvenue.jsp" ).forward( request, response ); // tu à la page bienvenue
		}
		
//		else { session.setAttribute( "isConnected", true ); //session est ok (vrai)
//		request.getRequestDispatcher( "/error.jsp" ).forward( request, response );
//		}
		// error si les mail et password ne sont pa égale
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
			
	}}}


