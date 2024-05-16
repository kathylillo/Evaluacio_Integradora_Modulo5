package cl.alke_wallet_evaluacion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.UserService;
import cl.alke_wallet_evaluacion.service.impl.UserServiceImpl;

/**
 * Servlet que maneja las solicitudes relacionadas con el inicio de sesión de usuario.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService = new UserServiceImpl();

    /**
     * Maneja las solicitudes GET para mostrar la página de inicio de sesión.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    /**
     * Maneja las solicitudes POST para autenticar al usuario e iniciar sesión.
     * Si las credenciales son válidas, inicia una sesión y redirige al dashboard.
     * Si las credenciales son inválidas, muestra un mensaje de error en la página de inicio de sesión.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error de servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.authenticateUser(username, password);

        if (user != null) {
            // Iniciar sesión creando una sesión HTTP y guardando el usuario autenticado
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // Mostrar mensaje de error en la página de inicio de sesión
            request.setAttribute("error", "Credenciales inválidas. Inténtalo de nuevo.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}