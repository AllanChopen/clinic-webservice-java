<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.clinica.dao.UsuarioDAO" %>
<%@ page import="com.clinica.model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");


    if (usuario == null || !"recepcionista".equals(usuario.getRol())) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Recepcionista</title>
</head>
<body>
    <h1>Crear Paciente</h1>

    <!-- Formulario para crear un usuario con rol de paciente -->
    <form action="UsuarioServlet" method="POST">
        <input type="hidden" name="accion" value="crear">
        <input type="hidden" name="rol" value="paciente">

        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" required><br><br>

        <label for="email">Email:</label>
        <input type="email" name="email" required><br><br>

        <label for="password">Contraseña:</label>
        <input type="password" name="password" required><br><br>

        <input type="submit" value="Crear Paciente">
    </form>
 
<h1>Crear Cita Médica</h1>
<form action="CitaServlet" method="POST">
    <label for="nombre_paciente">Nombre del Paciente:</label>
    <input type="text" name="nombre_paciente" required><br><br>

    <label for="fecha">Fecha:</label>
    <input type="date" name="fecha" required><br><br>

    <label for="hora">Hora:</label>
    <input type="time" name="hora" required><br><br>

    <label for="descripcion">Descripción:</label>
    <input type="text" name="descripcion" required><br><br>

    <input type="submit" value="Crear Cita">
</form>


<%@ page import="java.util.List" %>
<%@ page import="com.clinica.model.Cita" %>

<html>
<head>
    <title>Historial de Citas</title>
</head>
<body>
   <h1>Buscar Citas</h1>
    <form action="BuscarCitasPorNombreServlet" method="post">
        <label for="nombre_paciente">Nombre del Paciente:</label>
        <input type="text" id="nombre_paciente" name="nombre_paciente">
        <input type="submit" value="Buscar">
    </form>

    <%
        List<Cita> citas = (List<Cita>) request.getAttribute("citas");
        if (citas != null && !citas.isEmpty()) {
    %>
        <table>
            <tr>
                <th>ID</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Descripción</th>
            </tr>
            <%
                for (Cita cita : citas) {
            %>
                <tr>
                    <td><%= cita.getId() %></td>
                    <td><%= cita.getFecha() %></td>
                    <td><%= cita.getHora() %></td>
                    <td><%= cita.getDescripcion() %></td>
                </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <p>No hay citas registradas para este paciente.</p>
    <%
        }
    %>
</body>
</html>


<a href="logout.jsp">Cerrar Sesión</a>
