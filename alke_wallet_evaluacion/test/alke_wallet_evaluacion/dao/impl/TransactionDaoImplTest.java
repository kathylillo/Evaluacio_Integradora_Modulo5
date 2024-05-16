package alke_wallet_evaluacion.dao.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.alke_wallet_evaluacion.dao.impl.TransactionDaoImpl;
import cl.alke_wallet_evaluacion.model.Transaction;



/**
 * Pruebas unitarias para la implementación de TransactionDaoImpl.
 */
public class TransactionDaoImplTest {
    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private TransactionDaoImpl transactionDao;

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    /**
     * Prueba el método getAllTransactionsByUserId para obtener todas las transacciones de un usuario por su ID.
     */
    @Test
    @DisplayName("Prueba obtener todas las transacciones por ID de usuario")
    public void testGetAllTransactionsByUserId() throws SQLException {
        int userId = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("transaction_id")).thenReturn(1);
        when(mockResultSet.getInt("user_id")).thenReturn(userId);
        when(mockResultSet.getBigDecimal("amount")).thenReturn(new BigDecimal("100.00"));
        when(mockResultSet.getInt("type_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("transaction_date")).thenReturn(Timestamp.valueOf(LocalDateTime.now()));

        List<Transaction> transactions = transactionDao.getAllTransactionsByUserId(userId);

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(1, transactions.get(0).getId());
        assertEquals(userId, transactions.get(0).getUserId());
        assertEquals(new BigDecimal("100.00"), transactions.get(0).getAmount());
        assertEquals(1, transactions.get(0).getTypeId());
    }

    /**
     * Prueba el método addTransaction para agregar una nueva transacción.
     */
    @Test
    @DisplayName("Prueba agregar una nueva transacción")
    public void testAddTransaction() throws SQLException {
        Transaction transaction = new Transaction(1, 1, new BigDecimal("100.00"), 1, LocalDateTime.now());

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = transactionDao.addTransaction(transaction);

        assertTrue(result);
    }

    /**
     * Prueba el método recordTransaction para registrar una nueva transacción.
     */
    @Test
    @DisplayName("Prueba registrar una nueva transacción")
    public void testRecordTransaction() throws SQLException {
        int userId = 1;
        BigDecimal amount = new BigDecimal("100.00");
        int typeId = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = transactionDao.recordTransaction(userId, amount, typeId);

        assertTrue(result);
    }
}