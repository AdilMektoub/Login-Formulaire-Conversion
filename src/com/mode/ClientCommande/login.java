package com.mode.ClientCommande;

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
@WebServlet("/login")
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 	
			throws ServletException, IOException { 

		String login = request.getParameter( "txtLogin" );
		String password = request.getParameter( "txtPassword" );
		

		if ( login == null ) login = "";
		if ( password == null ) password = "";

		HttpSession session = request.getSession( true );
		session.setAttribute( "login", login );
		session.setAttribute( "password", password );

		request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 

		String nom = request.getParameter( "txtNom" ); //champs de textes
		String password = request.getParameter( "txtPassword" );
		int id_client = 0 ;
		String errorNom = ""; // si l'email est pas bon
		String errorPassword = ""; // si le password est mauvais
		
		ArrayList<Commande> commandes = new ArrayList<Commande>();//qui est vide pour l'instant (contenir commandes du client connecter)

		HttpSession session = request.getSession( true ); //crée une session

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Créer un projet qui permet aux utilisateurs de se logger et de voir leurs commandes
		
		String url = "jdbc:mysql://localhost:3306/clientcommande";// on met pas espace
		String loginDB = "root";
		String passwordDB = "";
		

		boolean Goodcnx = false;

		
		try (Connection cnx = DriverManager.getConnection(url, loginDB, passwordDB)) {// copié/coller

			//essaie de se connecter
			//creée un string pour faire/mettre une requête SQL
			String strSqlSelect = "SELECT * FROM client";

			
			try( Statement statement = cnx.createStatement() ) { // recolte pour pouvoir se connecter COPIÉ/COLLÉ
				ResultSet rs = statement.executeQuery(strSqlSelect); // enregistre le resultat de mon SQL (LES LIGNES USERS "SELECT")
				while (rs.next()) { //parcourir les resultats de mes selects à achaque fois querr j'en trouve un
					int rsIdClient = rs.getInt("id_client"); // dés que je trouve le l'ID je vais les stockés dans les variables "RS"
					String rsNom = rs.getString("nom"); // nom
					String rsPrenom = rs.getString("prenom"); // id
					String rsPassword = rs.getString("password");
	////////////////////////////////////////////////////////////////// juste pour se connecter
					

					if (rsNom.equals(nom) && rsPassword.equals(password)) { // pour voir si le nom/password/ est bien le login qui correspond à la BDD et si c'est = 1 (admin) 
						session.setAttribute( "nom", nom );
						session.setAttribute( "prenom", rsPrenom );
						session.setAttribute( "password", password );
						session.setAttribute( "id", rsIdClient );
						id_client = rsIdClient;
						Goodcnx = true; // tu te connecte
					}
					
					if (!rsNom.equals(nom)) {
						errorNom = "Votre nom ne figure pas dans la BDD";
						System.out.println(errorNom);
						session.setAttribute("errorNom", errorNom);
					}
					if (!rsPassword.equals(password)) {
						errorPassword = "Votre password est faux";
						System.out.println(errorPassword);
						session.setAttribute("errorPassword", errorPassword);
					}
					
				}
				

			}
			String strSqlCmd = "SELECT * FROM cmd WHERE id_client = '"+id_client+"';";
			try( Statement statement = cnx.createStatement() ) { //accéder à la BDD
				ResultSet rs = statement.executeQuery(strSqlCmd);
				while (rs.next()) {
					int rsIdCommande = rs.getInt("id_commande");
					int rsIdClient = rs.getInt("id_client");
					String rsProduit = rs.getString("produit");
					int rsNombre = rs.getInt("nombre");
					int  rsPrix = rs.getInt("prix");
					Commande c = new Commande(rsIdCommande, id_client, rsProduit, rsNombre, rsPrix); // chercher la classe OBJET COMMANDE
					
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
