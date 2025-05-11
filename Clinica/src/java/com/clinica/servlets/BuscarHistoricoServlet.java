package com.clinica.servlets;

import com.clinica.dao.DiagnosticoDAO;
import com.clinica.dao.RecetaDAO;
import com.clinica.model.Diagnostico;
import com.clinica.model.Receta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/BuscarHistoricoServlet")
public class BuscarHistoricoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombrePaciente = request.getParameter("nombre_paciente");

        try {
            RecetaDAO recetaDAO = new RecetaDAO();
            List<Receta> recetas = recetaDAO.buscarRecetasPorNombre(nombrePaciente);

            DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
            List<Diagnostico> diagnosticos = diagnosticoDAO.buscarDiagnosticosPorNombre(nombrePaciente);

            request.setAttribute("recetas", recetas);
            request.setAttribute("diagnosticos", diagnosticos);
            request.getRequestDispatcher("medico.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("medico.jsp?error=Error al buscar los registros");
        }
    }
    
    
}

