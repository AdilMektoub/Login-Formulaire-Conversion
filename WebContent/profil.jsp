<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<title>Formulaire de contact Adil</title>
	<link rel="stylesheet" type="text/css" href="design/default.css">
	<link rel="icon" type="image/pngn" href="img/favicon.png">
</head>
<html>
<head>
<meta charset="UTF-8">
<title>profil</title>
</head>
<body>
    <head>
        <meta charset="UTF-8" />
        <title>Veuillez vous authentifier</title>
        <link rel='stylesheet' type='text/css' href='styles.css' />
    </head>
    
    <body>
    <section>
		<div id="login-body">
        	<h1>Veuillez vous authentifier !</h1>
	<form method="post" action="bienvenue">
            <label for='txtNom'>Nom :</label>
            <input id='txtNom' name='txtNom' type='text' value='${nom}' autofocus /> <br/>
            <label for='txtAge'>Age :</label>
            <input id='txtAge' name='txtAge' type='text' value='${age}' autofocus /> <br/>
            <label for='txtEmail'>Email :</label>
            <input id='txtEmail' name='txtEmail' type='text' value='${email}' autofocus /> <br/>
            <input name='btnConnect' type='submit' /> <br/>
        </form>
</body>
</html>