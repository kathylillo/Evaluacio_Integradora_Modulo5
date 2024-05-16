package alke_wallet_evaluacion.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.alke_wallet_evaluacion.controller.DepositServlet;
import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.UserService;
import cl.alke_wallet_evaluacion.service.impl.TransactionServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;
/**
 * Pruebas unitarias para la clase DepositServlet.
 */
public class DepositServletTest {

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
    private DepositServlet depositServlet;

    /**
     * Prepara el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        depositServlet = new DepositServlet();
        injectMock(depositServlet, "userService", userService);
        injectMock(depositServlet, "transactionService", transactionService);
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
     * Prueba el comportamiento del método doPost cuando un usuario ha iniciado sesión y proporciona un monto válido.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Depósito exitoso con monto válido")
    public void testDoPost_UserLoggedIn_ValidAmount() throws ServletException, IOException {
        User user = new User();
        user.setUserId(1);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("amount")).thenReturn("100.00");
        when(userService.deposit(1, new BigDecimal("100.00"))).thenReturn(true);

        depositServlet.doPost(request, response);

        verify(userService).deposit(1, new BigDecimal("100.00"));
        verify(transactionService).recordTransaction(1, new BigDecimal("100.00"), "DEPOSIT");
        verify(response).sendRedirect(request.getContextPath() + "/dashboard");
    }

    /**
     * Prueba el comportamiento del método doPost cuando un usuario ha iniciado sesión pero proporciona un monto inválido.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Depósito falla con monto inválido")
    public void testDoPost_UserLoggedIn_InvalidAmount() throws ServletException, IOException {
        User user = new User();
        user.setUserId(1);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("amount")).thenReturn("-100.00");

        depositServlet.doPost(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/views/error_page.jsp");
    }

    /**
     * Prueba el comportamiento del método doPost cuando un usuario ha iniciado sesión pero proporciona un monto no numérico.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Depósito falla con monto no numérico")
    public void testDoPost_UserLoggedIn_NonNumericAmount() throws ServletException, IOException {
        User user = new User();
        user.setUserId(1);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("amount")).thenReturn("abc");

        depositServlet.doPost(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/views/error_page.jsp");
    }

    /**
     * Prueba el comportamiento del método doPost cuando ningún usuario ha iniciado sesión.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Depósito falla cuando ningún usuario ha iniciado sesión")
    public void testDoPost_UserNotLoggedIn() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);

        depositServlet.doPost(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/login");
    }

    /**
     * Prueba el comportamiento del método doPost cuando el depósito falla.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Depósito falla cuando la transacción no se puede completar")
    public void testDoPost_DepositFails() throws ServletException, IOException {
        User user = new User();
        user.setUserId(1);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("amount")).thenReturn("100.00");
        when(userService.deposit(1, new BigDecimal("100.00"))).thenReturn(false);

        depositServlet.doPost(request, response);

        verify(userService).deposit(1, new BigDecimal("100.00"));
        verify(response).sendRedirect(request.getContextPath() + "/views/error_page.jsp");
    }
}