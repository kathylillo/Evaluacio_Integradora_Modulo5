package alke_wallet_evaluacion.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cl.alke_wallet_evaluacion.model.User;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase User.
 */
public class UserTest {
	
    private User user;

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        user = new User(1, "KathyLillo", "12345", BigDecimal.ZERO);
    }

    /**
     * Prueba el método getUserId para obtener el ID del usuario.
     */
    @Test
    @DisplayName("Prueba obtener el ID del usuario")
    public void testGetUserId() {
        assertEquals(1, user.getUserId());
    }

    /**
     * Prueba el método setUserId para establecer el ID del usuario.
     */
    @Test
    @DisplayName("Prueba establecer el ID del usuario")
    public void testSetUserId() {
        user.setUserId(2);
        assertEquals(2, user.getUserId());
    }

    /**
     * Prueba el método getUsername para obtener el nombre de usuario.
     */
    @Test
    @DisplayName("Prueba obtener el nombre de usuario")
    public void testGetUsername() {
        assertEquals("KathyLillo", user.getUsername());
    }

    /**
     * Prueba el método setUsername para establecer el nombre de usuario.
     */
    @Test
    @DisplayName("Prueba establecer el nombre de usuario")
    public void testSetUsername() {
        user.setUsername("KathyReyes");
        assertEquals("KathyReyes", user.getUsername());
    }

    /**
     * Prueba el método getPassword para obtener la contraseña del usuario.
     */
    @Test
    @DisplayName("Prueba obtener la contraseña del usuario")
    public void testGetPassword() {
        assertEquals("12345", user.getPassword());
    }

    /**
     * Prueba el método setPassword para establecer la contraseña del usuario.
     */
    @Test
    @DisplayName("Prueba establecer la contraseña del usuario")
    public void testSetPassword() {
        user.setPassword("new54321");
        assertEquals("new54321", user.getPassword());
    }

    /**
     * Prueba el método getBalance para obtener el saldo del usuario.
     */
    @Test
    @DisplayName("Prueba obtener el saldo del usuario")
    public void testGetBalance() {
        assertEquals(BigDecimal.ZERO, user.getBalance());
    }

    /**
     * Prueba el método setBalance para establecer el saldo del usuario.
     */
    @Test
    @DisplayName("Prueba establecer el saldo del usuario")
    public void testSetBalance() {
        BigDecimal newBalance = new BigDecimal("100.50");
        user.setBalance(newBalance);
        assertEquals(newBalance, user.getBalance());
    }
}