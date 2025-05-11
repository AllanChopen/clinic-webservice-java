<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.clinica.dao.UsuarioDAO" %>
<%@ page import="com.clinica.model.Usuario" %>

<%

    Usuario usuario = (Usuario) session.getAttribute("usuario");


    if (usuario == null || !"administrador".equals(usuario.getRol())) {
        response.sendRedirect("index.jsp");
        return;
    }

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<Usuario> listaUsuarios = usuarioDAO.obtenerTodosLosUsuarios();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Administrador</title>
</head>
<body>
    <h1>Administrador</h1>

    <h2>Crear Nuevo Usuario</h2>
    <form action="UsuarioServlet" method="POST">
        <input type="hidden" name="accion" value="crear">

        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" required><br><br>

        <label for="email">Email:</label>
        <input type="email" name="email" required><br><br>

        <label for="password">Contraseña:</label>
        <input type="password" name="password" required><br><br>

        <label for="rol">Rol:</label>
        <select name="rol" required>
            <option value="administrador">Administrador</option>
            <option value="medico">Médico</option>
            <option value="recepcionista">Recepcionista</option>
            <option value="paciente">Paciente</option>
        </select><br><br>

        <input type="submit" value="Crear Usuario">
    </form>

    <h2>Usuarios Registrados</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Rol</th>
            <th>Acciones</th>
        </tr>
        <%
            for (Usuario u : listaUsuarios) {
        %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getNombre() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRol() %></td>
            <td>
                <form action="UsuarioServlet" method="POST" style="display:inline;">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="id" value="<%= u.getId() %>">
                    <input type="submit" value="Eliminar">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <a href="logout.jsp">Cerrar Sesión</a>
</body>
</html>
