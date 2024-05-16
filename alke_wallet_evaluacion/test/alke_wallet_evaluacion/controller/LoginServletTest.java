package alke_wallet_evaluacion.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.alke_wallet_evaluacion.controller.LoginServlet;
import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase LoginServlet.
 */
public class LoginServletTest {

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

    @InjectMocks
    private LoginServlet loginServlet;

    /**
     * Prepara el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginServlet = new LoginServlet();
        injectMock(loginServlet, "userService", userService);
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
     * Prueba el comportamiento del método doGet para obtener la página de inicio de sesión.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Obtener página de inicio de sesión")
    public void testDoGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("/views/login.jsp")).thenReturn(requestDispatcher);

        loginServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    /**
     * Prueba el comportamiento del método doPost cuando se proporcionan credenciales válidas.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Iniciar sesión con credenciales válidas")
    public void testDoPost_ValidCredentials() throws ServletException, IOException {
        String username = "validUser";
        String password = "validPass";
        User user = new User();
        user.setUserId(1);

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(userService.authenticateUser(username, password)).thenReturn(user);
        when(request.getSession()).thenReturn(session);

        loginServlet.doPost(request, response);

        verify(userService).authenticateUser(username, password);
        verify(session).setAttribute("user", user);
        verify(response).sendRedirect(request.getContextPath() + "/dashboard");
    }

    /**
     * Prueba el comportamiento del método doPost cuando se proporcionan credenciales inválidas.
     *
     * @throws ServletException si ocurre un problema relacionado con el servlet
     * @throws IOException      si ocurre una excepción de E/S
     */
    @Test
    @DisplayName("Iniciar sesión con credenciales inválidas")
    public void testDoPost_InvalidCredentials() throws ServletException, IOException {
        String username = "invalidUser";
        String password = "invalidPass";

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(userService.authenticateUser(username, password)).thenReturn(null);
        when(request.getRequestDispatcher("/views/login.jsp")).thenReturn(requestDispatcher);

        loginServlet.doPost(request, response);

        verify(userService).authenticateUser(username, password);
        verify(request).setAttribute("error", "Credenciales inválidas. Inténtalo de nuevo.");
        verify(requestDispatcher).forward(request, response);
    }
}