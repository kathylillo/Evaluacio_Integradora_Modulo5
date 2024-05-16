package cl.alke_wallet_evaluacion.dao;

import java.math.BigDecimal;
import java.util.List;

import cl.alke_wallet_evaluacion.model.Transaction;

/**
 * Interfaz que define las operaciones CRUD para las transacciones en el sistema.
 */
public interface TransactionDao {
	

	 /**
     * Obtiene todas las transacciones realizadas por un usuario específico.
     * @param userId El identificador único del usuario.
     * @return Una lista de transacciones realizadas por el usuario.
     */
    List<Transaction> getAllTransactionsByUserId(int userId);

    /**
     * Agrega una nueva transacción a la base de datos.
     * @param transaction La transacción a agregar.
     * @return true si la transacción se agregó exitosamente, false en caso contrario.
     */
    boolean addTransaction(Transaction transaction);

    /**
     * Registra una transacción en la base de datos.
     * @param userId El identificador único del usuario.
     * @param amount El monto de la transacción.
     * @param typeId El identificador del tipo de transacción.
     * @return true si se registró la transacción exitosamente, false en caso contrario.
     */
    boolean recordTransaction(int userId, BigDecimal amount, int typeId);

    /**
     * Obtiene todas las transacciones almacenadas en la base de datos.
     * @return Una lista de todas las transacciones almacenadas.
     */
    List<Transaction> getAllTransactions();

}