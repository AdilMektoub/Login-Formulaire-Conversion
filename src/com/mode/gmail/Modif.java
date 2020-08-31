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


@WebServlet("/modif")
public class Modif extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter( "txtNom" );
		String prenom = request.getParameter( "txtPrenom" );
		String email = request.getParameter( "txtEmail" );
		String password = request.getParameter( "txtPassword" );
		String age = request.getParameter( "txtAge" );
		String ville = request.getParameter( "txtVille" );
		
		HttpSession session = request.getSession( true );
		String id = (String) session.getAttribute("id");
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/gmail";
		String loginDB = "root";
		String passwordDB = "";
		
		try (Connection cnx = DriverManager.getConnection(url, loginDB, passwordDB)) {

			String strSqlUpdate = "UPDATE profils SET nom='"+nom+"', prenom='"+prenom+"' ,email='"+email+"', password='"+password+"', age="+age+", ville='"+ville+"' WHERE id= "+id+";";

			try(	Statement statement = cnx.createStatement() ) {
				statement.executeUpdate(strSqlUpdate);
				session.setAttribute( "prenom", prenom );
				session.setAttribute( "password", password );
				session.setAttribute( "nom", nom );
				session.setAttribute( "email", email );
				session.setAttribute( "age", age );
				session.setAttribute( "ville", ville );
				session.setAttribute( "id", id );
				System.out.println(prenom +" " + nom +" est bien modifié");
				request.getRequestDispatcher( "/ConnectedAndModif.jsp" ).forward( request, response );
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	
	
	}

}
