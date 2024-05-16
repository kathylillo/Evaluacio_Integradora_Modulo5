package alke_wallet_evaluacion.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

import cl.alke_wallet_evaluacion.controller.WithdrawServlet;
import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.UserService;
import cl.alke_wallet_evaluacion.service.impl.TransactionServiceImpl;

import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase WithdrawServlet.
 */
public class WithdrawServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @Mock
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private WithdrawServlet withdrawServlet;

    /**
     * Prepara el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        withdrawServlet = new WithdrawServlet();
        injectMock(withdrawServlet, "userService", userService);
        injectMock(withdrawServlet, "transactionService", transactionService);
    }

    /**
     * Método auxiliar para inyectar mocks en campos privados de un objeto.
     */
    private void injectMock(Object target, String fieldName, Object mock) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, mock);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prueba el comportamiento del método doPost cuando un usuario con sesión iniciada realiza un retiro válido.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Realizar retiro válido con usuario iniciado sesión")
    public void testDoPost_UserLoggedIn_ValidAmount() throws ServletException, IOException {
        User user = new User();
        user.setUserId(1);
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal currentBalance = new BigDecimal("200.00");

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("amount")).thenReturn("100.00");
        when(userService.getBalance(user.getUserId())).thenReturn(currentBalance);
        when(userService.withdraw(user.getUserId(), amount)).thenReturn(true);

        withdrawServlet.doPost(request, response);

        verify(userService).getBalance(user.getUserId());
        verify(userService).withdraw(user.getUserId(), amount);
        verify(transactionService).recordTransaction(user.getUserId(), amount, "WITHDRAWAL");
        verify(response).sendRedirect(request.getContextPath() + "/dashboard");
    }

    /**
     * Prueba el comportamiento del método doPost cuando se proporciona un monto de retiro inválido.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Manejar retiro con monto inválido")
    public void testDoPost_UserLoggedIn_InvalidAmount() throws ServletException, IOException {
        User user = new User();
        user.setUserId(1);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("amount")).thenReturn("invalid");

        withdrawServlet.doPost(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/views/error_page.jsp");
    }

    /**
     * Prueba el comportamiento del método doPost cuando un usuario no ha iniciado sesión.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Manejar retiro sin usuario iniciado sesión")
    public void testDoPost_UserNotLoggedIn() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);

        withdrawServlet.doPost(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/login");
    }

    /**
     * Prueba el comportamiento del método doPost cuando se intenta realizar un retiro que excede el saldo disponible.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Manejar retiro que excede el saldo disponible")
    public void testDoPost_UserLoggedIn_AmountExceedsBalance() throws ServletException, IOException {
        User user = new User();
        user.setUserId(1);
        BigDecimal amount = new BigDecimal("300.00");
        BigDecimal currentBalance = new BigDecimal("200.00");

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("amount")).thenReturn("300.00");
        when(userService.getBalance(user.getUserId())).thenReturn(currentBalance);

        withdrawServlet.doPost(request, response);

        verify(userService).getBalance(user.getUserId());
        verify(response).sendRedirect(request.getContextPath() + "/views/error_page.jsp");
    }

    /**
     * Prueba el comportamiento del método doPost cuando falla el retiro.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Manejar falla en el retiro")
    public void testDoPost_WithdrawFails() throws ServletException, IOException {
        User user = new User();
        user.setUserId(1);
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal currentBalance = new BigDecimal("200.00");

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("amount")).thenReturn("100.00");
        when(userService.getBalance(user.getUserId())).thenReturn(currentBalance);
        when(userService.withdraw(user.getUserId(), amount)).thenReturn(false);

        withdrawServlet.doPost(request, response);

        verify(userService).getBalance(user.getUserId());
        verify(userService).withdraw(user.getUserId(), amount);
        verify(response).sendRedirect(request.getContextPath() + "/views/error_page.jsp");
    }
}