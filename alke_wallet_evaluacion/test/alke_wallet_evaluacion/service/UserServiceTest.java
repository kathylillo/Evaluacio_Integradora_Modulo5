package alke_wallet_evaluacion.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.UserService;
import cl.alke_wallet_evaluacion.service.impl.UserServiceImpl;

/**
 * Pruebas unitarias para la clase UserService.
 */
public class UserServiceTest {

    private UserService userService;

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }
    /**
     * Prueba el método authenticateUser con credenciales válidas.
     */
    @Test
    @DisplayName("Prueba autenticación exitosa con credenciales válidas")
    void testAuthenticateUser_ValidCredentials() {
        String username = "JuanPablo";
        String password = "Profe12345";

        User authenticatedUser = userService.authenticateUser(username, password);

        assertNotNull(authenticatedUser, "El usuario autenticado no debería ser nulo");
        assertEquals(username, authenticatedUser.getUsername(), "El nombre de usuario debe ser el esperado");
    }

    /**
     * Prueba el método authenticateUser con credenciales inválidas.
     */
    @Test
    @DisplayName("Prueba autenticación fallida con credenciales inválidas")
    void testAuthenticateUser_InvalidCredentials() {
        String username = "Kathy";
        String password = "invalidpassword";

        User authenticatedUser = userService.authenticateUser(username, password);

        assertNull(authenticatedUser, "El usuario autenticado debe ser nulo para credenciales inválidas");
    }

    /**
     * Prueba el método getBalance para obtener el saldo de un usuario.
     */
    @Test
    @DisplayName("Prueba obtener saldo de usuario")
    void testGetBalance() {
        int userId = 1;

        BigDecimal balance = userService.getBalance(userId);

        assertNotNull(balance, "El saldo no debería ser nulo");
        assertTrue(balance.compareTo(BigDecimal.ZERO) >= 0, "El saldo debe ser mayor o igual a cero");
    }

    /**
     * Prueba el método deposit para realizar un depósito en la cuenta de un usuario.
     */
    @Test
    @DisplayName("Prueba depósito en cuenta de usuario")
    void testDeposit() {
        int userId = 1;
        BigDecimal amount = new BigDecimal("100.00");

        boolean depositResult = userService.deposit(userId, amount);

        assertTrue(depositResult, "El depósito debe realizarse correctamente");
    }

    /**
     * Prueba el método withdraw para realizar un retiro de la cuenta de un usuario.
     */
    @Test
    @DisplayName("Prueba retiro de cuenta de usuario")
    void testWithdraw() {
        int userId = 1;
        BigDecimal amount = new BigDecimal("50.00");

        boolean withdrawResult = userService.withdraw(userId, amount);

        assertTrue(withdrawResult, "El retiro debe realizarse correctamente");
    }
}