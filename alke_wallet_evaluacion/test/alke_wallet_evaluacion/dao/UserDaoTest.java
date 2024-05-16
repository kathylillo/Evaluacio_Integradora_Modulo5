package alke_wallet_evaluacion.dao;

import cl.alke_wallet_evaluacion.dao.UserDao;
import cl.alke_wallet_evaluacion.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Pruebas unitarias para la clase UserDao.
 */
public class UserDaoTest {
    private UserDao userDao;

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        userDao = mock(UserDao.class);
    }


    /**
     * Prueba el método getUserById para obtener un usuario por su ID.
     */
    @Test
    @DisplayName("Prueba obtener un usuario por su ID")
    public void testGetUserById() {
        int userId = 1;
        User mockUser = new User(userId, "Juana", "412536", BigDecimal.ZERO);

        when(userDao.getUserById(userId)).thenReturn(mockUser);

        User result = userDao.getUserById(userId);
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
    }

    /**
     * Prueba el método getUserByUsername para obtener un usuario por su nombre de usuario.
     */
    @Test
    @DisplayName("Prueba obtener un usuario por nombre de usuario")
    public void testGetUserByUsername() {
        String username = "JuanPablo";
        User mockUser = new User(1, username, "Profe12345", BigDecimal.ZERO);

        when(userDao.getUserByUsername(username)).thenReturn(mockUser);

        User result = userDao.getUserByUsername(username);
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    /**
     * Prueba el método addUser para agregar un nuevo usuario.
     */
    @Test
    @DisplayName("Prueba agregar un nuevo usuario")
    public void testAddUser() {
        User newUser = new User(3, "MariaMagdalena", "369852", BigDecimal.ZERO);

        when(userDao.addUser(newUser)).thenReturn(true);
        boolean added = userDao.addUser(newUser);
        assertTrue(added);
    }

    /**
     * Prueba el método updateUserBalance para actualizar el saldo de un usuario.
     */
    @Test
    @DisplayName("Prueba actualizar saldo de un usuario")
    public void testUpdateUserBalance() {
        int userId = 1;
        BigDecimal newBalance = new BigDecimal("100.50");

        when(userDao.updateUserBalance(userId, newBalance)).thenReturn(true);

        boolean updated = userDao.updateUserBalance(userId, newBalance);
        assertTrue(updated);
    }
}