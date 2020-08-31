<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Veuillez vous authentifier</title>
        <link rel='stylesheet' type='text/css' href='styles.css' />
    </head>
    <body>
        <h1>Bienvenue JSP !</h1>
    
        <form method="post" action="profil">
            <label>Nom :</label>
            <input id='txtNom' name='txtNom' type='text' value='${nom}' autofocus /> <br/>
            
            <label>Prenom :</label>
            <input id='txtPrenom' name='txtPrenom' type='text' value='${ prenom }' /> <br/>
            
            <label>Email :</label>
            <input id='txtEmail' name='txtEmail' type='text' value='${email}' /> <br/>
            
            <label>Adresse :</label>
            <input id='txtAdresse' name='txtAdresse' type='text' value='${ adresse }' /> <br/>
            
            <label>Mot de passe :</label>
            <input name='txtPassword' type='password' value='${ password }' /> <br/>
            <br/>
            <input name='btnConnect' type='submit' /> <br/>
        </form>     
    </body>
</html>