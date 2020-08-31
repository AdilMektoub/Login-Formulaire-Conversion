package com.mode.gmail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Ajout
 */
@WebServlet("/ajouter")
public class Ajout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter( "txtNom" );
		String prenom = request.getParameter( "txtPrenom" );
		String email = request.getParameter( "txtEmail" );
		String password = request.getParameter( "txtPassword" );
		String age = request.getParameter( "txtAge" );
		String ville = request.getParameter( "txtVille" );
		
		HttpSession session = request.getSession( true );
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/gmail";
		String loginDB = "root";
		String passwordDB = "";
		
		try (Connection cnx = DriverManager.getConnection(url, loginDB, passwordDB)) {

			String strSqlUpdate = "INSERT INTO profils ( nom, prenom, email, password, age, ville) VALUES ('"+nom+"', '"+prenom+"', '"+email+"', '"+password+"', "+age+", '"+ville+"');";

			try(	Statement statement = cnx.createStatement() ) {
				statement.executeUpdate(strSqlUpdate);
				session.setAttribute( "prenom", prenom );
				session.setAttribute( "nom", nom );
				System.out.println(prenom +" " + nom +" est bien ajouté");
				doGet(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	
	
	}

}
