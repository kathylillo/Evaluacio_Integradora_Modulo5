package cl.alke_wallet_evaluacion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import cl.alke_wallet_evaluacion.dao.*;
import cl.alke_wallet_evaluacion.dao.impl.*;
import cl.alke_wallet_evaluacion.model.Transaction;
import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.TransactionService;
import cl.alke_wallet_evaluacion.service.UserService;
import cl.alke_wallet_evaluacion.service.impl.TransactionServiceImpl;
import cl.alke_wallet_evaluacion.service.impl.UserServiceImpl;

/**
 * Servlet que maneja las solicitudes GET para la página de dashboard.
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService = new UserServiceImpl();
    TransactionDao transactionDao = new TransactionDaoImpl(); 
    TransactionService transactionService = new TransactionServiceImpl(transactionDao);

    /**
     * Maneja las solicitudes GET para mostrar el dashboard del usuario.
     * Obtiene información del usuario y sus transacciones, y redirige a la vista correspondiente.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error de servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            BigDecimal balance = userService.getBalance(user.getUserId());
            List<Transaction> transactions = transactionService.getAllTransactionsByUserId(user.getUserId());

            request.setAttribute("user", user);
            request.setAttribute("balance", balance);
            request.setAttribute("transactions", transactions);

            request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}