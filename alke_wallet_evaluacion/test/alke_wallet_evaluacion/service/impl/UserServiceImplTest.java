package alke_wallet_evaluacion.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;


import org.junit.jupiter.api.Test;

import cl.alke_wallet_evaluacion.dao.UserDao;
import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.impl.UserServiceImpl;
/**
 * Pruebas unitarias para la implementación de UserServiceImpl.
 */
public class UserServiceImplTest {

    @Mock
    private UserDao mockedUserDao;

    @InjectMocks
    private UserServiceImpl userService;

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Prueba el método authenticateUser con credenciales válidas.
     */
    @Test
    @DisplayName("Prueba autenticación exitosa con credenciales válidas")
    public void testAuthenticateUser_ValidCredentials() {
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
    public void testAuthenticateUser_InvalidCredentials() {
        when(mockedUserDao.getUserByUsername("Pablo")).thenReturn(null);

        User authenticatedUser = userService.authenticateUser("Pablo", "98765");

        assertNull(authenticatedUser);
    }

    /**
     * Prueba el método getBalance cuando el usuario existe.
     */
    @Test
    @DisplayName("Prueba obtener saldo cuando el usuario existe")
    public void testGetBalance_UserExists() {
        User user = new User(2, "KathyLillo", "12345", new BigDecimal("100.00"));
        when(mockedUserDao.getUserById(2)).thenReturn(user);

        BigDecimal balance = userService.getBalance(2);

        assertEquals(new BigDecimal("100.00"), balance);
    }

    /**
     * Prueba el método getBalance cuando el usuario no existe.
     */
    @Test
    @DisplayName("Prueba obtener saldo cuando el usuario no existe")
    public void testGetBalance_UserNotExists() {
        when(mockedUserDao.getUserById(100)).thenReturn(null);

        BigDecimal balance = userService.getBalance(100);

        assertEquals(BigDecimal.ZERO, balance);
    }

    /**
     * Prueba el método deposit cuando el usuario existe.
     */
    @Test
    @DisplayName("Prueba depósito exitoso cuando el usuario existe")
    public void testDeposit_UserExists() {
        User user = new User(2, "KathyLillo", "12345", BigDecimal.ZERO);
        when(mockedUserDao.getUserById(2)).thenReturn(user);
        when(mockedUserDao.updateUserBalance(2, new BigDecimal("50.00"))).thenReturn(true);

        boolean result = userService.deposit(2, new BigDecimal("50.00"));

        assertTrue(result);
    }

    /**
     * Prueba el método deposit cuando el usuario no existe.
     */
    @Test
    @DisplayName("Prueba depósito fallido cuando el usuario no existe")
    public void testDeposit_UserNotExists() {
        when(mockedUserDao.getUserById(100)).thenReturn(null);

        boolean result = userService.deposit(100, new BigDecimal("50.00"));

        assertFalse(result);
    }

    /**
     * Prueba el método withdraw cuando el usuario existe y tiene saldo suficiente.
     */
    @Test
    @DisplayName("Prueba retiro exitoso cuando el usuario tiene saldo suficiente")
    public void testWithdraw_UserExistsWithSufficientBalance() {
        User user = new User(1, "JuanPablo", "Profe12345", new BigDecimal("100.00"));
        when(mockedUserDao.getUserById(1)).thenReturn(user);
        when(mockedUserDao.updateUserBalance(1, new BigDecimal("80.00"))).thenReturn(true);

        boolean result = userService.withdraw(1, new BigDecimal("20.00"));

        assertTrue(result);
    }

    /**
     * Prueba el método withdraw cuando el usuario existe pero no tiene saldo suficiente.
     */
    @Test
    @DisplayName("Prueba retiro fallido cuando el usuario no tiene saldo suficiente")
    public void testWithdraw_UserExistsInsufficientBalance() {
        User user = new User(1, "JuanPablo", "Profe12345", new BigDecimal("50.00"));
        when(mockedUserDao.getUserById(1)).thenReturn(user);

        boolean result = userService.withdraw(1, new BigDecimal("100.00"));

        assertFalse(result);
    }

    /**
     * Prueba el método withdraw cuando el usuario no existe.
     */
    @Test
    @DisplayName("Prueba retiro fallido cuando el usuario no existe")
    public void testWithdraw_UserNotExists() {
        when(mockedUserDao.getUserById(100)).thenReturn(null);

        boolean result = userService.withdraw(100, new BigDecimal("20.00"));

        assertFalse(result);
    }
}