package alke_wallet_evaluacion.dao;

import cl.alke_wallet_evaluacion.dao.TransactionDao;
import cl.alke_wallet_evaluacion.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase TransactionDao.
 */
public class TransactionDaoTest {

    private TransactionDao transactionDao;

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        transactionDao = mock(TransactionDao.class);
    }

    /**
     * Prueba el método getAllTransactionsByUserId para obtener transacciones por ID de usuario.
     */
    @Test
    @DisplayName("Prueba obtener transacciones por ID de usuario")
    public void testGetAllTransactionsByUserId() {
        int userId = 1;
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction(1, userId, new BigDecimal("50.75"), 1, null));

        when(transactionDao.getAllTransactionsByUserId(userId)).thenReturn(mockTransactions);

        List<Transaction> result = transactionDao.getAllTransactionsByUserId(userId);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getUserId());
    }

    /**
     * Prueba el método addTransaction para agregar una nueva transacción.
     */
    @Test
    @DisplayName("Prueba agregar una nueva transacción")
    public void testAddTransaction() {
        Transaction newTransaction = new Transaction(2, 1001, new BigDecimal("100.00"), 2, null);

        when(transactionDao.addTransaction(newTransaction)).thenReturn(true);

        boolean added = transactionDao.addTransaction(newTransaction);
        assertTrue(added);
    }

    /**
     * Prueba el método recordTransaction para registrar una nueva transacción.
     */
    @Test
    @DisplayName("Prueba registrar una nueva transacción")
    public void testRecordTransaction() {
        int userId = 1;
        BigDecimal amount = new BigDecimal("75.50");
        int typeId = 1;
        when(transactionDao.recordTransaction(userId, amount, typeId)).thenReturn(true);

        boolean recorded = transactionDao.recordTransaction(userId, amount, typeId);
        assertTrue(recorded);
    }

    /**
     * Prueba el método getAllTransactions para obtener todas las transacciones.
     */
    @Test
    @DisplayName("Prueba obtener todas las transacciones")
    public void testGetAllTransactions() {
        List<Transaction> mockAllTransactions = new ArrayList<>();
        mockAllTransactions.add(new Transaction(1, 1001, new BigDecimal("50.75"), 1, null));
        mockAllTransactions.add(new Transaction(2, 1002, new BigDecimal("100.00"), 2, null));

        when(transactionDao.getAllTransactions()).thenReturn(mockAllTransactions);

        List<Transaction> result = transactionDao.getAllTransactions();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}