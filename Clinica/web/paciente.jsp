<%@ page import="com.clinica.model.Receta" %>
<%@ page import="com.clinica.model.Diagnostico" %>
<%@ page import="java.util.List"%>

<h1>Buscar Historial de Paciente</h1>
<form action="VerHistorialServlet" method="POST">
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

<%@ page import="com.clinica.model.Cita" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Hitorial de citas</h1>
<body>

<form action="BuscarCitasPacienteServlet" method="post">
    <label for="nombre_paciente">Nombre del Paciente:</label>
    <input type="text" name="nombre_paciente" id="nombre_paciente" required>
    <input type="submit" value="Buscar Citas">
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
        <% for (Cita cita : citas) { %>
        <tr>
            <td><%= cita.getId() %></td>
            <td><%= cita.getFecha() %></td>
            <td><%= cita.getHora() %></td>
            <td><%= cita.getDescripcion() %></td>
        </tr>
        <% } %>
    </table>
<% } else if (request.getParameter("error") != null) { %>
    <p><%= request.getParameter("error") %></p>
<% } %>

</body>
</html>


<a href="logout.jsp">Cerrar Sesión</a>