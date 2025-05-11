<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesión</title>
</head>
<body>
    <h2>Iniciar Sesión</h2>
    <form action="LoginServlet" method="POST">
        <label for="email">Correo:</label>
        <input type="email" name="email" required><br><br>

        <label for="password">Contraseña:</label>
        <input type="password" name="password" required><br><br>

        <input type="submit" value="Iniciar Sesión">
    </form>
</body>
</html>
 
<%@ page import="com.clinica.model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario != null) {
        String rol = usuario.getRol();
        if ("administrador".equals(rol)) {
            response.sendRedirect("admin.jsp");
        } else if ("recepcionista".equals(rol)) {
            response.sendRedirect("recepcionista.jsp");
        } else if ("medico".equals(rol)) {
            response.sendRedirect("medico.jsp");
        } else if ("paciente".equals(rol)) {
            response.sendRedirect("paciente.jsp");
        }
    }
%>
