package com.clinica.servlets;

import com.clinica.dao.CitaDAO;
import com.clinica.model.Cita;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/BuscarCitasPacienteServlet")
public class BuscarCitasPacienteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombrePaciente = request.getParameter("nombre_paciente");

        try {
            CitaDAO citaDAO = new CitaDAO();
            List<Cita> citas = citaDAO.buscarCitasPorNombre(nombrePaciente);

            request.setAttribute("citas", citas);
            request.getRequestDispatcher("paciente.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("paciente.jsp?error=Error al buscar citas");
        }
    }
}
