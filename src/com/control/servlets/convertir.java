package com.control.servlets;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class convertir
 */
@WebServlet("/convertir")
public class convertir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public convertir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher( "/convertir.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String montant = request.getParameter( "montant" ); // name de l'INPUT TEXTE 
		HttpSession session = request.getSession( true );
		session.setAttribute( "montant", montant );
		request.setAttribute( "montant", montant );
		
		System.out.println( "in the doPost" );
		int i=Integer.parseInt(montant); // je CONVERTIE les String en entier
		
		if (i > 10000) { // si sup. a 10 000 
			session.setAttribute( "isConnected", true ); //session est ok (vrai)
			request.getRequestDispatcher( "/error.jsp" ).forward( request, response );// tu vas sur la page erros
		}
		
		if ( montant != null) { // si il est pas vide 
			session.setAttribute( "isConnected", true ); // tu te connecte
			request.getRequestDispatcher( "/conversion.jsp" ).forward( request, response ); // à la page convertir.jsp
			double usd = 1.14; //crée une varialble pour le change
			double resultat; // crée varialbe pour le resultat du change
			resultat = (i*usd); // CONVERSION de l'euros au dollar
			session.setAttribute( "resultat", resultat );
		} 
		

		else if (i < 0){
			session.setAttribute( "isConnected", false );
			request.getRequestDispatcher( "/convertir.jsp" ).forward( request, response );
		}
	}

}
