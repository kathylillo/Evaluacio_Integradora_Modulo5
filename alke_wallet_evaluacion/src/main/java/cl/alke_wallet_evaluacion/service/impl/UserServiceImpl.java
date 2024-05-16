package cl.alke_wallet_evaluacion.service.impl;

import java.math.BigDecimal;

import cl.alke_wallet_evaluacion.dao.UserDao;
import cl.alke_wallet_evaluacion.dao.impl.UserDaoImpl;
import cl.alke_wallet_evaluacion.model.User;
import cl.alke_wallet_evaluacion.service.UserService;

/**
 * Implementación de la interfaz UserService que proporciona servicios relacionados con los usuarios.
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    /**
     * Constructor que inicializa la implementación de UserDao.
     */ 
    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }
   
    @Override
    public User authenticateUser(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public BigDecimal getBalance(int userId) {
        User user = userDao.getUserById(userId);
        if (user != null) {
            return user.getBalance();
        }
        return BigDecimal.ZERO;
    }

    @Override 
    public boolean deposit(int userId, BigDecimal amount) {
        User user = userDao.getUserById(userId);
        if (user != null) {
            BigDecimal currentBalance = user.getBalance();
            BigDecimal newBalance = currentBalance.add(amount);
            return userDao.updateUserBalance(userId, newBalance);
        }
        return false;
    }

    @Override
    public boolean withdraw(int userId, BigDecimal amount) {
        User user = userDao.getUserById(userId);
        if (user != null) {
            BigDecimal currentBalance = user.getBalance();
            if (currentBalance.compareTo(amount) >= 0) {
                BigDecimal newBalance = currentBalance.subtract(amount);
                return userDao.updateUserBalance(userId, newBalance);
            }
        }
        return false;
    }
    
}
