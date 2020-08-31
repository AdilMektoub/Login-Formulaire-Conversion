<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel='stylesheet' type='text/css' href='styles.css' />
<title>Connecté</title>
</head>
<body>
<h1>je suis connecté</h1>
<br>
        <form method="post" action="modif">
            <label for='txtNom'>Nom :</label>
            <input id='txtNom' name='txtNom' type='text' value='${nom}' autofocus /> <br/>
            <label for='txtPrenom'>Prenom :</label>
            <input name='txtPrenom' type='text' value='${prenom}' /> <br/>
            <label for='txtEmail'>Email :</label>
            <input id='txtEmail' name='txtEmail' type='text' value='${email}' autofocus /> <br/>
            <label for='txtPassword'>Password :</label>
            <input name='txtPassword' type='text' value='${password}' /> <br/>
            <label for='txtAge'>Age :</label>
            <input name='txtAge' type='text' value='${age}' /> <br/>
            <label for='txtVille'>Ville :</label>
            <input name='txtVille' type='text' value='${ville}' /> <br/>
            <br/>
            <input name='btnConnect' value='Modifier' type='submit' /> <br/>
        </form>  
</body>
</html>