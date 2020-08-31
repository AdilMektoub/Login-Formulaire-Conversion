<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL</title>
</head>
<body>

<h1>JSTL</h1>
<br>
<c:forEach var="item" items="${list}" >
	<c:out value="${item}" /><br>
</c:forEach>
<h1>JSTL les éléments négatifs de tab</h1>
<br>
<c:forEach var="item2" items="${list2}" >
	<c:out value="${item2}" /><br>
</c:forEach>
</body>
</html>