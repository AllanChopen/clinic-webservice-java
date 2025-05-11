package com.clinica.servlets;

import com.clinica.dao.CitaDAO;
import com.clinica.dao.UsuarioDAO;
import com.clinica.model.Cita;
import com.clinica.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/CitaServlet")
public class CitaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombrePaciente = request.getParameter("nombre_paciente");

           
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario paciente = usuarioDAO.obtenerUsuarioPorNombre(nombrePaciente);

            if (paciente == null) {
                response.sendRedirect("recepcionista.jsp?error=Paciente no encontrado");
                return;
            }

            LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
            LocalTime hora = LocalTime.parse(request.getParameter("hora"));
            String descripcion = request.getParameter("descripcion");

            Cita nuevaCita = new Cita(0, paciente.getId(), fecha, hora, descripcion);
            CitaDAO citaDAO = new CitaDAO();
            citaDAO.crearCita(nuevaCita);

            response.sendRedirect("recepcionista.jsp?mensaje=Cita creada exitosamente");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("recepcionista.jsp?error=Error al crear la cita");
        }
    }
}
