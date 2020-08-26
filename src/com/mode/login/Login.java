package com.mode.login;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login") // => annotation qui decrit notre classe Login pour dire que c une servlet
// ("/login") le paramettre de @WebServlet pour decrir l'URL de la servlet
public class Login extends HttpServlet { // ma classe Login hérite de la classe HttpServlet (pour dire que c une servlet)
	
	private static final long serialVersionUID = 1L;
	// dans chaque servlet on a des methodes par défault appélées (ex : doGet(), doPost(), init(), service() etc...)
	
	// doGet() prend en paramettre deux objets un objet HttpServletRequest et un objet HttpServletResponse
	// HttpServletResponse represente la réponse HTTP
	// HttpServletRequest represente la requette HTTP
	// est la méthode qui renvoie la réponse au client (navigateur) après avoir reçu la requete du client 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// veut dire que la méthode doGet() est vulnérable et peut declencher les exceptions ServletException IOException
		
		String login = request.getParameter( "txtLogin" );
		// chaque appel de la doGet()je déclare un String login 
		// qui accede à la request passée par le client
		//  et accede au paramettre "txtLogin" (JSP)=>  <input id='txtLogin' name='txtLogin' type='text' value='${login}' autofocus />
		String password = request.getParameter( "txtPassword" );
		// chaque appel de la doGet()je déclare un String password 
		// qui accede à la requette passée par le client
		//  et accede au paramettre "txtPassword" (JSP)=>   <input name='txtPassword' type='password' value='${password}' /> <br/>
		
		if ( login == null ) login = "";
		if ( password == null ) password = "";
		// Si je me connecte à ma servlet Login pour la premiere fois login et password (les deux String qui represente
		// les paramettres de la requette de client) seront == à null
		// SI il sont null je leur donne la valeur d'une String vide
		
		HttpSession session = request.getSession( true );
		// session est un objet de type HttpSession qui va acceder à la requette du client (request)
		// essayer d'acceder à la session de request (requette de client) 
		// (true) si il trouve pas une session existante il crée une nouvelle session
		
		// je declare un attribut de session appelé login qui prend la valeur de String login
		session.setAttribute( "login", login );
		// je declare un attribut de session appelé password qui prend la valeur de String password
		session.setAttribute( "password", password );
		// par la méthode getRequestDispatcher appliqué sur ma request et qui prend en paramettre ma JSP ( "/Login.jsp" )
		// j'obtiens un objet de type RequestDispatcher peut appelé la méthode forward qui prends en paramettres
		// une requette et une réponse HTTP et envoie cette paire à la JSP Login.jsp
		request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter( "txtLogin" );
		String password = request.getParameter( "txtPassword" ); //
		HttpSession session = request.getSession( true );
		session.setAttribute( "login", login );
		session.setAttribute( "password", password );
		request.setAttribute( "login", login );
		request.setAttribute( "password", password );
		
		System.out.println( "in the doPost" );
		if ( login.contains("@gmail.com") && password.equals( "007" ) ) {
			session.setAttribute( "isConnected", true );
			request.getRequestDispatcher( "/Connected.jsp" ).forward( request, response );
		} else {
			session.setAttribute( "isConnected", false );
			request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
		}
	}
}










































//package com.mode.login;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Date;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet("/login")
//public class Login extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	public Login() {
//		super();
//	}
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println( "In the service method" );
//		super.service(req, resp);
//	}
//	
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		System.out.println( "In the doGet" );
//		
//        String login = request.getParameter( "txtLogin" );
//        String password = request.getParameter( "txtPassword" );
////        String erreurLogin = ""; String erreurMP = "";
////        
////        if (login == null) erreurLogin = "";
////        else if (!login.equals("Bond") && login.length()>0) erreurLogin = "mauvais login";
////
////        if (password == null) erreurMP = "";
////        else if (!password.equals("007") && password.length()>0) erreurMP = "mauvais password";
//        
//        if ( login == null || !login.equals("Bond")) {
//        	login = "";	
//        }
//        if ( password == null || !password.equals("007") ) {
//        	password = ""; 
//        }
//		if (login.equals("Bond") ) { login = "Bond"; }
//        if ( !login.equals( "Bond" ) && !password.equals("007") ) {
//        	login = "";
//        	password = "";		
//        }
//		
//        this.getServletContext().getRequestDispatcher( "login.jsp" ).forward( request, response );
//
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String login = request.getParameter( "txtLogin" );
//		String password = request.getParameter( "txtPassword" );
////		String erreurLogin = ""; String erreurMP = "";
//		// j'ai donc mon login et mon Mot de passe
//		System.out.println( "in the doPost" );
//
//		if ( login.equals( "Bond" ) && password.equals( "007" ) ) {
//			response.setContentType( "text/html" );
//			try ( PrintWriter out = response.getWriter() ) {
//				out.println( "<h1>Vous etes connecté Bond!</h1>" );
//				out.println( "<h1><a href='/login'>Retour</a></h1>" );
//			}
//		}
//		else {
//			doGet( request, response );
//		}
//	}
//}





















//
//response.setContentType( "text/html" ); // choisir type de réponse
//
//try ( PrintWriter out = response.getWriter() ) {
//	out.println( "<!DOCTYPE html>" );
//	out.println( "<html>" );
//	out.println( "    <head>" );
//	out.println( "        <title>Veuillez vous identifier</title>" );
//	out.println( "        <link rel='stylesheet' type='text/css' href='styles.css' />" );
//	out.println( "    </head>" );
//	out.println( "    <body>" );
//	out.println( "        <h1>Veuillez vous identifier</h1>" );
//	out.println( "        <h2> " + new Date() +"</h2>" );
//	out.println( "        <form method='POST' action='login'>" );
//	out.println( "            <label for='txtLogin'>Login :</label>" ); 
//	out.println( "            <input id='txtLogin' name='txtLogin' type='text' value='" + login + "' autofocus /><br/>" );
//	out.println( "            <label for='txtPassword'>Password :</label>" ); 
//	out.println( "            <input name='txtPassword' type='password' value='"+ password + "' /><br/>" ); 
//	out.println( "            <br/>" );
//	out.println( "            <input name='btnConnect' type='submit' value='Se connecter' /><br/>" );
//	out.println( "        </form>" );
//	out.println( "    </body>" );
//	out.println( "</html>" );
//}