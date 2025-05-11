<%@ page import="com.clinica.model.Usuario" %>
<%@ page import="java.util.List"%>
<%
    Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
    if (usuarioSesion == null || !"medico".equals(usuarioSesion.getRol())) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<h1>Crear Receta Médica</h1>
<form action="RecetaServlet" method="POST">
    <label for="nombre_paciente">Nombre del Paciente:</label>
    <input type="text" name="nombre_paciente" required><br><br>

    <label for="descripcion">Descripción de la Receta:</label>
    <textarea name="descripcion" required></textarea><br><br>

    <input type="submit" value="Crear Receta">
</form>

<h1>Crear Diagnóstico Médico</h1>
<form action="DiagnosticoServlet" method="POST">
    <label for="nombre_paciente">Nombre del Paciente:</label>
    <input type="text" name="nombre_paciente" required><br><br>

    <label for="descripcion">Descripción del Diagnóstico:</label>
    <textarea name="descripcion" required></textarea><br><br>

    <input type="submit" value="Crear Diagnóstico">
</form>


<%
    if (request.getParameter("mensaje") != null) {
%>
    <p style="color: green;"><%= request.getParameter("mensaje") %></p>
<%
    } else if (request.getParameter("error") != null) {
%>
    <p style="color: red;"><%= request.getParameter("error") %></p>
<%
    }
%>


<%@ page import="com.clinica.model.Receta" %>
<%@ page import="com.clinica.model.Diagnostico" %>

<h1>Buscar Historial de Paciente</h1>
<form action="BuscarHistoricoServlet" method="POST">
    <label for="nombre_paciente">Nombre del Paciente:</label>
    <input type="text" name="nombre_paciente" required><br><br>
    <input type="submit" value="Buscar">
</form>

<h2>Recetas</h2>
<%
    List<Receta> recetas = (List<Receta>) request.getAttribute("recetas");
    if (recetas != null && !recetas.isEmpty()) {
%>
    <table border="1">
        <tr>
            <th>ID</th><th>Descripción</th><th>Fecha</th>
        </tr>
        <%
            for (Receta receta : recetas) {
        %>
        <tr>
            <td><%= receta.getId() %></td>
            <td><%= receta.getDescripcion() %></td>
            <td><%= receta.getFecha() %></td>
        </tr>
        <%
            }
        %>
    </table>
<%
    } else {
%>
    <p>No se encontraron recetas.</p>
<%
    }
%>

<h2>Diagnósticos</h2>
<%
    List<Diagnostico> diagnosticos = (List<Diagnostico>) request.getAttribute("diagnosticos");
    if (diagnosticos != null && !diagnosticos.isEmpty()) {
%>
    <table border="1">
        <tr>
            <th>ID</th><th>Descripción</th><th>Fecha</th>
        </tr>
        <%
            for (Diagnostico diagnostico : diagnosticos) {
        %>
        <tr>
            <td><%= diagnostico.getId() %></td>
            <td><%= diagnostico.getDescripcion() %></td>
            <td><%= diagnostico.getFecha() %></td>
        </tr>
        <%
            }
        %>
    </table>
<%
    } else {
%>
    <p>No se encontraron diagnósticos.</p>
<%
    }
%>

<a href="logout.jsp">Cerrar Sesión</a>