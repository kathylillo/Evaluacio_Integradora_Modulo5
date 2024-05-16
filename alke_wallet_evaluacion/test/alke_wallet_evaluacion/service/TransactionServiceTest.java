package alke_wallet_evaluacion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.alke_wallet_evaluacion.dao.TransactionDao;
import cl.alke_wallet_evaluacion.model.Transaction;
import cl.alke_wallet_evaluacion.service.impl.TransactionServiceImpl;

/**
 * Pruebas unitarias para la clase TransactionService.
 */
public class TransactionServiceTest {

    @Mock
    private TransactionDao transactionDao;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Prueba el método getAllTransactionsByUserId para obtener todas las transacciones de un usuario.
     */
    @Test
    @DisplayName("Prueba obtener todas las transacciones por ID de usuario")
    void testGetAllTransactionsByUserId() {
        int userId = 1;
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction(1, userId, BigDecimal.valueOf(100.0), 1, null)); // Simula una transacción de depósito
        expectedTransactions.add(new Transaction(2, userId, BigDecimal.valueOf(50.0), 2, null)); // Simula una transacción de retiro
        when(transactionDao.getAllTransactionsByUserId(userId)).thenReturn(expectedTransactions);

        List<Transaction> result = transactionService.getAllTransactionsByUserId(userId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("DEPOSIT", result.get(0).getTransactionType());
        assertEquals("WITHDRAWAL", result.get(1).getTransactionType());
    }

    /**
     * Prueba el método addTransaction para agregar una transacción.
     */
    @Test
    @DisplayName("Prueba agregar una transacción")
    void testAddTransaction() {
        Transaction transaction = new Transaction(1, 1, BigDecimal.valueOf(100.0), 1, null);
        when(transactionDao.addTransaction(transaction)).thenReturn(true);

        boolean result = transactionService.addTransaction(transaction);

        assertTrue(result);
    }

    /**
     * Prueba el método recordTransaction para registrar una transacción de depósito.
     */
    @Test
    @DisplayName("Prueba registrar una transacción de depósito")
    void testRecordTransaction_Deposit() {
        int userId = 1;
        BigDecimal amount = BigDecimal.valueOf(100.0);
        String transactionType = "DEPOSIT";
        when(transactionDao.addTransaction(any(Transaction.class))).thenReturn(true);

        boolean result = transactionService.recordTransaction(userId, amount, transactionType);

        assertTrue(result);
    }

    /**
     * Prueba el método recordTransaction para registrar una transacción de retiro.
     */
    @Test
    @DisplayName("Prueba registrar una transacción de retiro")
    void testRecordTransaction_Withdrawal() {
        int userId = 1;
        BigDecimal amount = BigDecimal.valueOf(50.0);
        String transactionType = "WITHDRAWAL";
        when(transactionDao.addTransaction(any(Transaction.class))).thenReturn(true);

        boolean result = transactionService.recordTransaction(userId, amount, transactionType);

        assertTrue(result);
    }

    /**
     * Prueba el método recordTransaction para un tipo de transacción desconocido.
     */
    @Test
    @DisplayName("Prueba registrar una transacción con tipo desconocido")
    void testRecordTransaction_UnknownTransactionType() {
        int userId = 1;
        BigDecimal amount = BigDecimal.valueOf(50.0);
        String transactionType = "UNKNOWN";

        assertThrows(IllegalArgumentException.class,
                () -> transactionService.recordTransaction(userId, amount, transactionType));
    }

    /**
     * Prueba el método getAllTransactions para obtener todas las transacciones.
     */
    @Test
    @DisplayName("Prueba obtener todas las transacciones")
    void testGetAllTransactions() {
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction(1, 1, BigDecimal.valueOf(100.0), 1, null)); // Simula una transacción de depósito
        expectedTransactions.add(new Transaction(2, 2, BigDecimal.valueOf(50.0), 2, null)); // Simula una transacción de retiro
        when(transactionDao.getAllTransactions()).thenReturn(expectedTransactions);

        List<Transaction> result = transactionService.getAllTransactions();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("DEPOSIT", result.get(0).getTransactionType());
        assertEquals("WITHDRAWAL", result.get(1).getTransactionType());
    }
}