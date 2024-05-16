package cl.alke_wallet_evaluacion.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

import cl.alke_wallet_evaluacion.dao.impl.TransactionDaoImpl;
import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.UserService;
import cl.alke_wallet_evaluacion.service.impl.*;

/**
 * Servlet que maneja las solicitudes POST para realizar depósitos en la cuenta del usuario.
 */
@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService = new UserServiceImpl();
    private final TransactionServiceImpl transactionService = new TransactionServiceImpl(new TransactionDaoImpl());
    /**
     * Maneja las solicitudes POST para realizar un depósito en la cuenta del usuario.
     * Valida el monto del depósito y lo agrega al saldo del usuario si es válido.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error de servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            BigDecimal amount;
            try {
                // Obtener el monto del parámetro 'amount' y convertirlo a BigDecimal
                amount = new BigDecimal(request.getParameter("amount"));

                // Validar que el monto sea mayor que cero
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    // Redirigir con un mensaje de error si el monto es menor o igual a cero
                    response.sendRedirect(request.getContextPath() + "/views/error_page.jsp");
                    return;
                }
            } catch (NumberFormatException e) {
                // Manejar el error si el parámetro 'amount' no es un número válido
                response.sendRedirect(request.getContextPath() + "/views/error_page.jsp");
                return;
            }

            // Realizar el depósito en la cuenta del usuario
            boolean depositResult = userService.deposit(user.getUserId(), amount);

            if (depositResult) {
                // Registrar la transacción de depósito
                transactionService.recordTransaction(user.getUserId(), amount, "DEPOSIT");
                response.sendRedirect(request.getContextPath() + "/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/views/error_page.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}