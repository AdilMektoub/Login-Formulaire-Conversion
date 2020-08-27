<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Convertir</title>
</head>
<body>
<h1>Convertir les euros en dollar </h1><br>
<h2>Veuillez choisir la somme a convertir</h2>
<form method="post" action="convertir">
            <label for='montant'>montant en EUROS</label>
            <input id='montant' name='montant' type="number" value='${montant}' autofocus /> <br/>
            <input name='btnConnect' type='submit' /> <br/>
        </form>
</body>
</html>