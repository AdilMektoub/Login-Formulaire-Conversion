<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class = fon>
<h1>Bienvenue Mr ${login} </h1>
        <p>Ceci est une page générée depuis une JSP.</p>
        <p>
            <% 
            String attribut = (String) request.getAttribute("login");
            out.println( attribut );
            %>
        </p>
<a href='/cours_login/courslogin'>Retour</a>
<img alt="" src="https://media.giphy.com/media/3o7TKFMaiRP4Vhtgdi/giphy.gif">
</body>
</html>