package com.clinica.servlets;

import com.clinica.dao.RecetaDAO;
import com.clinica.dao.UsuarioDAO;
import com.clinica.model.Receta;
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

@WebServlet("/RecetaServlet")
public class RecetaServlet extends HttpServlet {

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

            Receta receta = new Receta(0, paciente.getId(), medico.getId(), descripcion, fecha);
            RecetaDAO recetaDAO = new RecetaDAO();
            recetaDAO.crearReceta(receta);

            response.sendRedirect("medico.jsp?mensaje=Receta creada exitosamente");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("medico.jsp?error=Error al crear la receta");
        }
    }
}
