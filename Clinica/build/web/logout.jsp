<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cierre de Sesión</title>
</head>
<body>
    <%
        // Obtén la sesión existente (no crea una nueva si no existe)
        HttpSession currentSession = request.getSession(false);
        if (currentSession != null) {
            currentSession.invalidate(); // Invalida la sesión
        }
    %>
    <h1>Has cerrado sesión exitosamente</h1>
    <a href="index.jsp">Volver al inicio</a>
</body>
</html>
