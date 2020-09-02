package com.entities;

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

public class LoginCommande extends HttpServlet { 


	private static final long serialVersionUID = 1L; 


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 	
			throws ServletException, IOException { 

		request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 

		String nom = request.getParameter( "txtLogin" );
		String password = request.getParameter( "txtPassword" );
		int id_client = 0;

		ArrayList<Commande> commandes = new ArrayList<Commande>();

		HttpSession session = request.getSession( true );

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/clientcommande";
		String loginDB = "root";
		String passwordDB = "";

		boolean Goodcnx = false;

		try (Connection cnx = DriverManager.getConnection(url, loginDB, passwordDB)) {

			String strSqlSelect = "SELECT * FROM client;";

			try( Statement statement = cnx.createStatement() ) {
				ResultSet rs = statement.executeQuery(strSqlSelect);
				while (rs.next()) {
					String rsNom = rs.getString("nom");
					String rsPassword = rs.getString("password");
					int rsId = rs.getInt("id_client");
					String rsPrenom = rs.getString("prenom");

					if ( rsNom.equals(nom) && rsPassword.equals(password)) {
						session.setAttribute( "nom", nom );
						session.setAttribute( "prenom", rsPrenom );
						session.setAttribute( "password", password );
						session.setAttribute( "id", rsId );
						id_client = rsId;
						Goodcnx = true;
					}
				}

			}
			String strSqlSelectCmd = "SELECT * FROM cmd WHERE id_client = '"+id_client+"';";
			try( Statement statement = cnx.createStatement() ) {
				ResultSet rs = statement.executeQuery(strSqlSelectCmd);
				while (rs.next()) {
					String rsProduit = rs.getString("produit");
					int rsNombre = rs.getInt("nombre");
					int rsPrix = rs.getInt("prix");
					int rsId_cmd = rs.getInt("id_commande");
					Commande c = new Commande(rsId_cmd, id_client, rsProduit, rsNombre, rsPrix);

					commandes.add(c);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!Goodcnx)	
			request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
		else if (Goodcnx) {
			session.setAttribute( "commandes", commandes );
			request.getRequestDispatcher( "/Connected.jsp" ).forward( request, response );
		}

	}
}
