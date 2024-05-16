package alke_wallet_evaluacion.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cl.alke_wallet_evaluacion.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;



/**
 * Pruebas unitarias para la clase Transaction.
 */
public class TransactionTest {
	
    private Transaction transaction;

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     */
    @BeforeEach
    public void setUp() {
        transaction = new Transaction(1, 1001, new BigDecimal("50.75"), 1, LocalDateTime.now());
    }

    /**
     * Prueba el método getId para obtener el ID de la transacción.
     */
    @Test
    @DisplayName("Prueba obtener el ID de la transacción")
    public void testGetId() {
        assertEquals(1, transaction.getId());
    }

    /**
     * Prueba el método setId para establecer el ID de la transacción.
     */
    @Test
    @DisplayName("Prueba establecer el ID de la transacción")
    public void testSetId() {
        transaction.setId(2);
        assertEquals(2, transaction.getId());
    }

    /**
     * Prueba el método getUserId para obtener el ID de usuario asociado a la transacción.
     */
    @Test
    @DisplayName("Prueba obtener el ID de usuario asociado a la transacción")
    public void testGetUserId() {
        assertEquals(1001, transaction.getUserId());
    }

    /**
     * Prueba el método setUserId para establecer el ID de usuario asociado a la transacción.
     */
    @Test
    @DisplayName("Prueba establecer el ID de usuario asociado a la transacción")
    public void testSetUserId() {
        transaction.setUserId(2002);
        assertEquals(2002, transaction.getUserId());
    }

    /**
     * Prueba el método getAmount para obtener el monto de la transacción.
     */
    @Test
    @DisplayName("Prueba obtener el monto de la transacción")
    public void testGetAmount() {
        assertEquals(new BigDecimal("50.75"), transaction.getAmount());
    }

    /**
     * Prueba el método setAmount para establecer el monto de la transacción.
     */
    @Test
    @DisplayName("Prueba establecer el monto de la transacción")
    public void testSetAmount() {
        BigDecimal newAmount = new BigDecimal("100.00");
        transaction.setAmount(newAmount);
        assertEquals(newAmount, transaction.getAmount());
    }

    /**
     * Prueba el método getTypeId para obtener el tipo de la transacción.
     */
    @Test
    @DisplayName("Prueba obtener el tipo de la transacción")
    public void testGetTypeId() {
        assertEquals(1, transaction.getTypeId());
    }

    /**
     * Prueba el método setTypeId para establecer el tipo de la transacción.
     */
    @Test
    @DisplayName("Prueba establecer el tipo de la transacción")
    public void testSetTypeId() {
        transaction.setTypeId(2);
        assertEquals(2, transaction.getTypeId());
    }

    /**
     * Prueba el método getDateTime para obtener la fecha y hora de la transacción.
     */
    @Test
    @DisplayName("Prueba obtener la fecha y hora de la transacción")
    public void testGetDateTime() {
        assertNotNull(transaction.getDateTime());
    }

    /**
     * Prueba el método setDateTime para establecer la fecha y hora de la transacción.
     */
    @Test
    @DisplayName("Prueba establecer la fecha y hora de la transacción")
    public void testSetDateTime() {
        LocalDateTime newDateTime = LocalDateTime.of(2024, 5, 15, 10, 30);
        transaction.setDateTime(newDateTime);
        assertEquals(newDateTime, transaction.getDateTime());
    }

    /**
     * Prueba el método getTransactionType para obtener el tipo de la transacción.
     */
    @Test
    @DisplayName("Prueba obtener el tipo de la transacción")
    public void testGetTransactionType() {
        assertNull(transaction.getTransactionType());
    }

    /**
     * Prueba el método setTransactionType para establecer el tipo de la transacción.
     */
    @Test
    @DisplayName("Prueba establecer el tipo de la transacción")
    public void testSetTransactionType() {
        String newTransactionType = "Deposit";
        transaction.setTransactionType(newTransactionType);
        assertEquals(newTransactionType, transaction.getTransactionType());
    }
}