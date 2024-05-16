package alke_wallet_evaluacion.share.model.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.alke_wallet_evaluacion.share.model.dao.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Pruebas unitarias para la clase DAO.
 */
public class DAOTest {

    @Mock
    private Connection mockConn;

    @Mock
    private Statement mockStmt;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private DAO dao = new DAO() {
        
    };

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Prueba el método getConnection para obtener una conexión.
     */
    @Test
    @DisplayName("Prueba obtener conexión exitosa")
    void testGetConnection() throws SQLException, ClassNotFoundException {
        when(mockConn.isClosed()).thenReturn(false);
        dao.conn = mockConn;

        Connection conn = dao.getConnection();
        assertNotNull(conn);
        assertEquals(mockConn, conn);

        verify(mockConn, times(1)).isClosed();
    }

    /**
     * Prueba el método consultar para ejecutar una consulta SQL.
     */
    @Test
    @DisplayName("Prueba consulta SQL exitosa")
    void testConsultar() throws SQLException {
        String sql = "SELECT * FROM tabla";

        when(mockConn.createStatement()).thenReturn(mockStmt);
        when(mockStmt.executeQuery(sql)).thenReturn(null); 
        dao.conn = mockConn;
        ResultSet resultSet = dao.consultar(sql);

        assertNull(resultSet);

        verify(mockConn, times(1)).createStatement();
        verify(mockStmt, times(1)).executeQuery(sql); 
    }

    /**
     * Prueba el método ejecutarSql para ejecutar una sentencia SQL de actualización.
     */
    @Test
    @DisplayName("Prueba ejecución de sentencia SQL exitosa")
    void testEjecutarSql() throws SQLException {
        String sql = "UPDATE tabla SET columna = valor";

        when(mockConn.createStatement()).thenReturn(mockStmt);
        when(mockStmt.executeUpdate(sql)).thenReturn(1);
        dao.conn = mockConn;

        int result = dao.ejecutarSql(sql);
        assertEquals(1, result);

        verify(mockConn, times(1)).createStatement();
        verify(mockStmt, times(1)).executeUpdate(sql);
    }

    /**
     * Prueba el método close para cerrar la conexión, la declaración y el conjunto de resultados.
     */
    @Test
    @DisplayName("Prueba cierre de recursos exitoso")
    void testClose() throws SQLException {
        dao.conn = mockConn;
        dao.stmt = mockStmt;
        dao.resultSet = mockResultSet;

        dao.close(mockConn, mockStmt, mockResultSet);

        verify(mockResultSet, times(1)).close();
        verify(mockStmt, times(1)).close();
        verify(mockConn, times(1)).close();
    }
}