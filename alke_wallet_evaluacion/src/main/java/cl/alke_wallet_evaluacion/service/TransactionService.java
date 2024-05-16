package cl.alke_wallet_evaluacion.service;

import java.math.BigDecimal;
import java.util.List;

import cl.alke_wallet_evaluacion.model.Transaction;

/**
 * Interfaz que define los servicios relacionados con las transacciones en el sistema.
 */
public interface TransactionService {

	/**
     * Obtiene todas las transacciones realizadas por un usuario específico.
     *
     * @param userId El identificador único del usuario.
     * @return Una lista de todas las transacciones realizadas por el usuario.
     */
    List<Transaction> getAllTransactionsByUserId(int userId);

    /**
     * Agrega una nueva transacción al sistema.
     *
     * @param transaction La transacción que se desea agregar.
     * @return true si la transacción se agregó correctamente, false si ocurrió algún error.
     */
    boolean addTransaction(Transaction transaction);

    /**
     * Registra una nueva transacción de depósito o retiro en la base de datos.
     *
     * @param userId El ID del usuario asociado a la transacción.
     * @param amount El monto de la transacción.
     * @param transactionType El tipo de transacción (DEPOSIT o WITHDRAWAL).
     * @return true si la transacción se registró correctamente, false si ocurrió algún error.
     */
    boolean recordTransaction(int userId, BigDecimal amount, String transactionType);

    /**
     * Obtiene todas las transacciones en el sistema.
     *
     * @return Una lista de todas las transacciones.
     */
    List<Transaction> getAllTransactions();
}