package alke_wallet_evaluacion.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.alke_wallet_evaluacion.controller.DashboardServlet;
import cl.alke_wallet_evaluacion.model.Transaction;
import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.TransactionService;
import cl.alke_wallet_evaluacion.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase DashboardServlet.
 */
public class DashboardServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private UserService userService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private DashboardServlet dashboardServlet;

    /**
     * Prepara el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dashboardServlet = new DashboardServlet();
        injectMock(dashboardServlet, "userService", userService);
        injectMock(dashboardServlet, "transactionService", transactionService);
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
     * Prueba el comportamiento del método doGet cuando un usuario ha iniciado sesión.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Prueba doGet cuando un usuario ha iniciado sesión")
    public void testDoGet_UserLoggedIn() throws ServletException, IOException {
        User user = new User();
        user.setUserId(1);

        BigDecimal saldoEsperado = new BigDecimal("300.50");
        List<Transaction> transacciones = Arrays.asList(new Transaction(), new Transaction());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(userService.getBalance(1)).thenReturn(saldoEsperado);
        when(transactionService.getAllTransactionsByUserId(1)).thenReturn(transacciones);
        when(request.getRequestDispatcher("/views/dashboard.jsp")).thenReturn(requestDispatcher);

        dashboardServlet.doGet(request, response);

        verify(userService).getBalance(1);
        verify(transactionService).getAllTransactionsByUserId(1);
        verify(request).setAttribute("user", user);
        verify(request).setAttribute("balance", saldoEsperado);
        verify(request).setAttribute("transactions", transacciones);
        verify(requestDispatcher).forward(request, response);
    }

    /**
     * Prueba el comportamiento del método doGet cuando ningún usuario ha iniciado sesión.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Prueba doGet cuando ningún usuario ha iniciado sesión")
    public void testDoGet_UserNotLoggedIn() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);

        dashboardServlet.doGet(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/login");
    }
}