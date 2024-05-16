package alke_wallet_evaluacion.dao.impl;


import cl.alke_wallet_evaluacion.dao.impl.UserDaoImpl;
import cl.alke_wallet_evaluacion.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la implementación de UserDaoImpl.
 */
public class UserDaoImplTest {

    private UserDaoImpl userDao;

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        userDao = new UserDaoImpl();
    }

    /**
     * Prueba el método getUserById para obtener un usuario por su ID.
     */
    @Test
    @DisplayName("Prueba obtener usuario por ID")
    public void testGetUserById() throws SQLException {
        int userId = 1;
        ResultSet rs = mock(ResultSet.class);
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt("user_id")).thenReturn(userId);
        when(rs.getString("username")).thenReturn("Soledad");
        when(rs.getString("password")).thenReturn("75364");
        when(rs.getBigDecimal("balance")).thenReturn(BigDecimal.ZERO);

        User user = userDao.getUserById(userId);
        assertNotNull(user);
        assertEquals(userId, user.getUserId());
    }

    /**
     * Prueba el método getUserByUsername para obtener un usuario por su nombre de usuario.
     */
    @Test
    @DisplayName("Prueba obtener usuario por nombre de usuario")
    public void testGetUserByUsername() throws SQLException {
        String username = "KathyLillo";
        ResultSet rs = mock(ResultSet.class);
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt("user_id")).thenReturn(1);
        when(rs.getString("username")).thenReturn("KathyLillo");
        when(rs.getString("password")).thenReturn("12345");
        when(rs.getBigDecimal("balance")).thenReturn(BigDecimal.ZERO);

        User user = userDao.getUserByUsername(username);

        assertNotNull(user, "El usuario obtenido no debe ser nulo");
        assertEquals(username, user.getUsername(), "El nombre de usuario debe coincidir");
    }

    /**
     * Prueba el método addUser para agregar un nuevo usuario.
     */
    @Test
    @DisplayName("Prueba agregar un nuevo usuario")
    public void testAddUser() throws SQLException {
        User newUser = new User(2, "Maria", "54321", BigDecimal.ZERO);
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);

        boolean added = userDao.addUser(newUser);

        assertTrue(added, "La inserción de usuario debería ser exitosa");
    }

    /**
     * Prueba el método updateUserBalance para actualizar el saldo de un usuario.
     */
    @Test
    @DisplayName("Prueba actualizar el saldo de un usuario")
    public void testUpdateUserBalance() throws SQLException {
        int userId = 1;
        BigDecimal newBalance = new BigDecimal("100.50");
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);

        boolean updated = userDao.updateUserBalance(userId, newBalance);
        assertTrue(updated);
    }
}