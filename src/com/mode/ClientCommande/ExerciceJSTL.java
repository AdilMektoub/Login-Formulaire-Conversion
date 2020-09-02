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
 * Servlet implementation class ExerciceJSTL
 */
@WebServlet("/jstl")
public class ExerciceJSTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExerciceJSTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//2 listes 
		ArrayList<String> listeDeProduit = new ArrayList<String>();
		listeDeProduit.add("adidas") ; listeDeProduit.add("Nike") ; 
		listeDeProduit.add("One+8") ; listeDeProduit.add("Rouge à levre") ; 
		listeDeProduit.add("armor") ; listeDeProduit.add("samsung") ;
		System.out.println(listeDeProduit);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//la 1 première liste contient la liste des produit listeDeProduit sauf les produit qui commence par la lettre a
		
		
		ArrayList<String> listeDeProduitCommencePasA = new ArrayList<String>();//qui est vide pour l'instant (contenir commandes du client connecter)
		for (String string : listeDeProduit) {
		if (string.charAt(0) != 'a') {
			listeDeProduitCommencePasA.add(string);
		}
		
	}
		//la  2eme liste contient la liste des nom des clients de notre DB
		ArrayList<String> listeContientNomClientsDB = new ArrayList<String>();
		
		HttpSession session = request.getSession( true ); //crée une session

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/clientcommande";// on met pas espace
		String loginDB = "root";
		String passwordDB = "";
		
		try (Connection cnx = DriverManager.getConnection(url, loginDB, passwordDB)) {// copié/coller

			//essaie de se connecter
			//creée un string pour faire/mettre une requête SQL
			String strSqlSelect = "SELECT * FROM client";

			
			try( Statement statement = cnx.createStatement() ) { // recolte pour pouvoir se connecter COPIÉ/COLLÉ
				ResultSet rs = statement.executeQuery(strSqlSelect); // enregistre le resultat de mon SQL (LES LIGNES USERS "SELECT")
				while (rs.next()) { //parcourir les resultats de mes selects à achaque fois querr j'en trouve un
					String rsNom = rs.getString("nom"); // nom
					
/////////////////////////////////  juste pour se connecter /////////////////////////////////////////////////////// 
					listeContientNomClientsDB.add(rsNom);	

			}
		}
		request.getRequestDispatcher("/Pop.jsp").forward(request, response);

		} catch (SQLException e) {
		e.printStackTrace();
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sans dopst que doget
	}

}
