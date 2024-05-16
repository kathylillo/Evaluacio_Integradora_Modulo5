package cl.alke_wallet_evaluacion.service.impl;

import java.math.BigDecimal;
import java.util.List;

import cl.alke_wallet_evaluacion.dao.TransactionDao;
import cl.alke_wallet_evaluacion.model.Transaction;
import cl.alke_wallet_evaluacion.service.TransactionService;

/**
 * Implementación de la interfaz TransactionService que proporciona servicios relacionados con las transacciones.
 */
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;

    /**
     * Constructor que inicializa la implementación de TransactionDao.
     */
    public TransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public List<Transaction> getAllTransactionsByUserId(int userId) {
    	List<Transaction> transactions = transactionDao.getAllTransactionsByUserId(userId);

        for (Transaction transaction : transactions) {
            if (transaction.getTypeId() == 1) {
                transaction.setTransactionType("DEPOSIT");
            } else if (transaction.getTypeId() == 2) {
                transaction.setTransactionType("WITHDRAWAL");
            } else {
                transaction.setTransactionType("UNKNOWN");
            }
        }

        return transactions;
    }
    
    @Override
    public boolean addTransaction(Transaction transaction) {
        if (transactionDao != null) {
            return transactionDao.addTransaction(transaction);
        } else {
            throw new RuntimeException("TransactionDao is not initialized");
        }
    }

    public boolean recordTransaction(int userId, BigDecimal amount, String transactionType) {
        int typeId;
        
        // Determinar el tipo de transacción según el parámetro transactionType
        if ("DEPOSIT".equalsIgnoreCase(transactionType)) {
            typeId = 1; // Tipo 1 para depósito
        } else if ("WITHDRAWAL".equalsIgnoreCase(transactionType)) {
            typeId = 2; // Tipo 2 para retiro
        } else {
            throw new IllegalArgumentException("Tipo de transacción desconocido: " + transactionType);
        }

        // Crear una nueva instancia de Transaction
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setTypeId(typeId);

        // Agregar la transacción utilizando el método addTransaction en TransactionDao
        return transactionDao.addTransaction(transaction);
        
    }

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactions = transactionDao.getAllTransactions();

	    for (Transaction transaction : transactions) {
	        if (transaction.getTypeId() == 1) {
	            transaction.setTransactionType("DEPOSIT");
	        } else if (transaction.getTypeId() == 2) {
	            transaction.setTransactionType("WITHDRAWAL");
	        } else {
	            transaction.setTransactionType("UNKNOWN");
	        }
	    }

	    return transactions;
	}
}