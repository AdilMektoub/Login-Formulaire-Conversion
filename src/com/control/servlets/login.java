package com.control.servlets;

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
		String login = request.getParameter( "txtLogin" );
		String email = request.getParameter( "txtEmail" );
		String password = request.getParameter( "txtPassword" ); //input text dans bienvenue.jsp
		String errorMail = ""; // si l'email est pas bon
		String errorPassword = ""; // si le password est mauvais
		HttpSession session = request.getSession( true );
		session.setAttribute( "email", email );
		session.setAttribute( "password", password );
		session.setAttribute("errorMail", errorMail);
		session.setAttribute("errorPassword", errorPassword);
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
		 
		 
////////////////////////////////////////////////////////////////////////////////////
			ArrayList<String> logins = new ArrayList<String>();
			ArrayList<String> logins2 = new ArrayList<String>();

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			String url = "jdbc:mysql://localhost:3306/jstl";
			String loginDB = "root";
			String passwordDB = "";

			boolean Goodcnx = false;
			boolean adminConnected = false;
			boolean userConnected = false;

			try (Connection cnx = DriverManager.getConnection(url, loginDB, passwordDB)) {

				//creée un string pour faire/mettre une requête SQL
				String strSqlSelect = "SELECT * FROM users;";

				
				try( Statement statement = cnx.createStatement() ) { // recolte pour pouvoir se connecter
					ResultSet rs = statement.executeQuery(strSqlSelect); // enregistre le resultat de mon SQL (LES LIGNES USERS "SELECT")
					while (rs.next()) { //parcourir les resultats de mes selects à achaque fois querr j'en trouve un
						String rsLogin = rs.getString("login"); // dés que je trouve le login je vais le stocké
						String rsPassword = rs.getString("password"); // password
						String rsId = rs.getString("id"); // id
						String rsAdmin = rs.getString("admin");// admin 

						if ( rsLogin.equals(login) && rsPassword.equals(password) && rsAdmin.equals("1")) {
							session.setAttribute( "login", login );
							session.setAttribute( "password", password );
							session.setAttribute( "id", rsId );
							adminConnected = true;
							Goodcnx = true;
						}
						else if( !rsLogin.equals(login)) {
							if (rsAdmin.equals("0"))
							logins.add(rsLogin + " est un user");
							else logins.add(rsLogin + " est un admin");
						}
					}

				}
				try( Statement statement = cnx.createStatement() ) {
					ResultSet rs = statement.executeQuery(strSqlSelect);
					while (rs.next()) {
						String rsLogin = rs.getString("login");
						String rsPassword = rs.getString("password");
						String rsId = rs.getString("id");
						String rsAdmin = rs.getString("admin");

						if ( rsLogin.equals(login) && rsPassword.equals(password) && rsAdmin.equals("0")) {
							session.setAttribute( "login", login );
							session.setAttribute( "password", password );
							session.setAttribute( "id", rsId );
							Goodcnx = true;
							userConnected = true;
							
						}
						else if( !rsLogin.equals(login) && rsAdmin.equals("0")) {
							logins2.add(rsLogin+" est un user");
						}
					}

				}


				

				if (Goodcnx && adminConnected) {
					session.setAttribute( "logins", logins );
					request.getRequestDispatcher( "/Connected.jsp" ).forward( request, response );
				}

				else if (Goodcnx && userConnected) {
					logins = logins2;
					session.setAttribute( "logins", logins );
					request.getRequestDispatcher( "/Connected.jsp" ).forward( request, response );
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (!Goodcnx)	
				request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
	}
}
