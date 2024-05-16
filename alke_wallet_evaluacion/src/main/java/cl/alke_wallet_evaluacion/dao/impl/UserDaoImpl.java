package cl.alke_wallet_evaluacion.dao.impl;
 
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cl.alke_wallet_evaluacion.dao.UserDao;
import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.share.model.dao.DAO;
/**
 * Implementación de la interfaz UserDao que permite interactuar con la tabla 'users' en la base de datos.
 */
public class UserDaoImpl extends DAO implements UserDao{
	@Override
    public User getUserById(int userId) {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                BigDecimal balance = rs.getBigDecimal("balance");
                user = new User(userId, username, password, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, stmt, rs);
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String password = rs.getString("password");
                BigDecimal balance = rs.getBigDecimal("balance");
                user = new User(userId, username, password, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, stmt, rs);
        }

        return user;
    }

    @Override
    public boolean addUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("INSERT INTO users (username, password, balance) VALUES (?, ?, ?)");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setBigDecimal(3, user.getBalance());

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
    public boolean updateUserBalance(int userId, BigDecimal newBalance) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("UPDATE users SET balance = ? WHERE user_id = ?");
            stmt.setBigDecimal(1, newBalance);
            stmt.setInt(2, userId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn, stmt, null);
        }
    }

    
}