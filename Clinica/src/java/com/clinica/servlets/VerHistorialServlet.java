package com.clinica.servlets;

import com.clinica.dao.DiagnosticoDAO;
import com.clinica.dao.RecetaDAO;
import com.clinica.model.Diagnostico;
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
import java.util.List;

@WebServlet("/VerHistorialServlet")
public class VerHistorialServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");

        if (usuarioSesion != null && "paciente".equals(usuarioSesion.getRol())) {
            String nombrePaciente = usuarioSesion.getNombre(); 

            try {
                RecetaDAO recetaDAO = new RecetaDAO();
                List<Receta> recetas = recetaDAO.buscarRecetasPorNombre(nombrePaciente);

                DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();
                List<Diagnostico> diagnosticos = diagnosticoDAO.buscarDiagnosticosPorNombre(nombrePaciente);

                request.setAttribute("recetas", recetas);
                request.setAttribute("diagnosticos", diagnosticos);
                request.getRequestDispatcher("paciente.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("paciente.jsp?error=Error al buscar los registros");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}

