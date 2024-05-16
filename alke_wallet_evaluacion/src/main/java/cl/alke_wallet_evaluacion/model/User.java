package cl.alke_wallet_evaluacion.model;

import java.math.BigDecimal;

/**
 * Clase que representa un usuario en el sistema.
 */
public class User {

    private int userId;
    private String username;
    private String password;
    private BigDecimal balance;

    /**
     * Constructor vacío de la clase User.
     */
    public User() {}

    /**
     * Constructor de la clase User.
     * @param userId Identificador único del usuario.
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param balance Saldo actual del usuario.
     */
    public User(int userId, String username, String password, BigDecimal balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    /**
     * Obtiene el identificador único del usuario.
     * @return El identificador único del usuario.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Establece el identificador único del usuario.
     * @param userId El nuevo identificador único del usuario.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Obtiene el nombre de usuario.
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * @param username El nuevo nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el saldo actual del usuario.
     * @return El saldo actual del usuario.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Establece el nuevo saldo del usuario.
     * @param newBalance El nuevo saldo del usuario.
     */
    public void setBalance(BigDecimal newBalance) {
        this.balance = newBalance;
    }
}