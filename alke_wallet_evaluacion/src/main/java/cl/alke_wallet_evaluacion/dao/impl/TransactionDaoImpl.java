package cl.alke_wallet_evaluacion.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cl.alke_wallet_evaluacion.dao.TransactionDao;
import cl.alke_wallet_evaluacion.model.Transaction;
import cl.alke_wallet_evaluacion.share.model.dao.DAO;
/**
 * Implementación de la interfaz TransactionDao que permite interactuar con la tabla 'transaction' en la base de datos.
 */
public class TransactionDaoImpl extends DAO implements TransactionDao{

	@Override
    public List<Transaction> getAllTransactionsByUserId(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM transaction WHERE user_id = ?");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                BigDecimal amount = rs.getBigDecimal("amount");
                int typeId = rs.getInt("type_id");
                LocalDateTime dateTime = rs.getTimestamp("transaction_date").toLocalDateTime();

                Transaction transaction = new Transaction(transactionId, userId, amount, typeId, dateTime);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, stmt, rs);
        }

        return transactions;
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("INSERT INTO transaction (user_id, amount, type_id) VALUES (?, ?, ?)");
            stmt.setInt(1, transaction.getUserId());
            stmt.setBigDecimal(2, transaction.getAmount());
            stmt.setInt(3, transaction.getTypeId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn, stmt, null);
        }
    }

	@Override
	public boolean recordTransaction(int userId, BigDecimal amount, int typeId) {
	        Connection conn = null;
	        PreparedStatement stmt = null;

	        try {
	            conn = getConnection();
	            stmt = conn.prepareStatement("INSERT INTO transaction (user_id, amount, type_id) VALUES (?, ?, ?)");
	            stmt.setInt(1, userId);
	            stmt.setBigDecimal(2, amount);
	            stmt.setInt(3, typeId);

	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            close(conn, stmt, null);
	        }
	    }

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactions = new ArrayList<>();
	    String query = "SELECT * FROM transactions";

	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Transaction transaction = new Transaction();
	            transaction.setId(rs.getInt("id"));
	            transaction.setUserId(rs.getInt("userId"));
	            transaction.setAmount(rs.getBigDecimal("amount"));
	            transaction.setTypeId(rs.getInt("typeId"));
	            transaction.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
	            transactions.add(transaction);
	        }
	    } catch (SQLException e) {
	        
	    }

	    return transactions;
	}
	

	
}