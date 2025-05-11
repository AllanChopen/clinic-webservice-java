package com.clinica.servlets;

import com.clinica.dao.UsuarioDAO;
import com.clinica.model.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            HttpSession session = request.getSession(false);
            Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");

            if ("crear".equals(accion)) {
                String nombre = request.getParameter("nombre");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String rol = request.getParameter("rol");

                // Validar que solo recepcionistas puedan crear pacientes
                if ("recepcionista".equals(usuarioSesion.getRol()) && !"paciente".equals(rol)) {
                    response.sendRedirect("recepcionista.jsp?error=No puedes crear este tipo de usuario");
                    return;
                }

                Usuario nuevoUsuario = new Usuario(0, nombre, email, password, rol);
                usuarioDAO.crearUsuario(nuevoUsuario);

                if ("recepcionista".equals(usuarioSesion.getRol())) {
                    response.sendRedirect("recepcionista.jsp?mensaje=Paciente creado exitosamente");
                } else {
                    response.sendRedirect("admin.jsp?mensaje=Usuario creado exitosamente");
                }

            } else if ("eliminar".equals(accion) && "administrador".equals(usuarioSesion.getRol())) {
                int id = Integer.parseInt(request.getParameter("id"));
                usuarioDAO.eliminarUsuario(id);
                response.sendRedirect("admin.jsp?mensaje=Usuario eliminado exitosamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=Error al procesar la solicitud");
        }
    }
}
