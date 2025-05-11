package com.clinica.servlets;

import com.clinica.dao.DiagnosticoDAO;
import com.clinica.dao.UsuarioDAO;
import com.clinica.model.Diagnostico;
import com.clinica.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/DiagnosticoServlet")
public class DiagnosticoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            Usuario medico = (Usuario) session.getAttribute("usuario");

            if (medico == null || !"medico".equals(medico.getRol())) {
                response.sendRedirect("index.jsp");
                return;
            }

            String nombrePaciente = request.getParameter("nombre_paciente");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario paciente = usuarioDAO.obtenerUsuarioPorNombre(nombrePaciente);

            if (paciente == null) {
                response.sendRedirect("medico.jsp?error=Paciente no encontrado");
                return;
            }

            String descripcion = request.getParameter("descripcion");
            LocalDate fecha = LocalDate.now();

            Diagnostico diagnostico = new Diagnostico(0, paciente.getId(), medico.getId(), descripcion, fecha);
            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
            diagnosticoDAO.crearDiagnostico(diagnostico);

            response.sendRedirect("medico.jsp?mensaje=Diagnóstico creado exitosamente");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("medico.jsp?error=Error al crear el diagnóstico");
        }
    }
}

