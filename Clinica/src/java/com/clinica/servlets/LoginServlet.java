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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.autenticarUsuario(email, password);

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);

                // Redirige según el rol del usuario
                switch (usuario.getRol()) {
                    case "administrador":
                        response.sendRedirect("admin.jsp");
                        break;
                    case "medico":
                        response.sendRedirect("medico.jsp");
                        break;
                    case "paciente":
                        response.sendRedirect("paciente.jsp");
                        break;
                    case "recepcionista":
                        response.sendRedirect("recepcionista.jsp");
                        break;
                    default:
                        response.sendRedirect("index.jsp");
                        break;
                }
            } else {
                response.sendRedirect("index.jsp?error=Credenciales inválidas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=Error en la autenticación");
        }
    }
}
