package com.mode.gmail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		
//		String email = request.getParameter( "txtEmail" );
//		String password = request.getParameter( "txtPassword" );
//
//		if ( email == null ) email = "";
//		if ( password == null ) password = "";
//		HttpSession session = request.getSession( true );
//		
//		session.setAttribute( "email", email );
//		session.setAttribute( "password", password );
		request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter( "txtEmail" );
		String password = request.getParameter( "txtPassword" );

		HttpSession session = request.getSession( true );
		boolean Goodcnx = false;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/gmail";
		String loginDB = "root";
		String passwordDB = "";

		try (Connection cnx = DriverManager.getConnection(url, loginDB, passwordDB)) {

			String strSqlSelect = "SELECT * FROM profils;"; 

			try(	Statement statement = cnx.createStatement() ) {
				ResultSet rs = statement.executeQuery(strSqlSelect);
				while (rs.next()) {

					//je crée des string pour chaque element recupérer
					String rsId = rs.getString("id");
					String rsPrenom = rs.getString("prenom");
					String rsPassword = rs.getString("password");
					String rsNom = rs.getString("nom");
					String rsEmail = rs.getString("email");
					String rsAge = rs.getString("age");
					String rsVille = rs.getString("ville");

					// et si il lemail et le password correponde bien " c'est bien la personne" 
					if ( rsEmail.equals(email) && rsPassword.equals(password)) { // modif.java si c'est bien la personne qui c'est logué
						session.setAttribute( "email", rsEmail );// tu modifie email
						session.setAttribute( "password", rsPassword );// tu modifie ...
						session.setAttribute( "id", rsId );// tu modifie...
						session.setAttribute( "nom", rsNom );// tu modifie..
						session.setAttribute( "prenom", rsPrenom );// tu modifie...
						session.setAttribute( "age", rsAge );// tu modifie...
						session.setAttribute( "ville", rsVille );// tu modifie...
						Goodcnx = true;
						request.getRequestDispatcher( "/ConnectedAndModif.jsp" ).forward( request, response );
					}
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (Goodcnx == false)	
			doGet(request, response);		
		
	}

}
