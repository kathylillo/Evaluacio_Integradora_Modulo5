package cl.alke_wallet_evaluacion.service;

import java.math.BigDecimal;

import cl.alke_wallet_evaluacion.model.User;

/**
 * Interfaz que define los servicios relacionados con los usuarios en el sistema.
 */
public interface UserService {

    /**
     * Autentica a un usuario en el sistema.
     * @param username El nombre de usuario del usuario.
     * @param password La contraseña del usuario.
     * @return El usuario autenticado si las credenciales son válidas, o null si la autenticación falla.
     */
    User authenticateUser(String username, String password);

    /**
     * Obtiene el saldo actual de un usuario.
     * @param userId El identificador único del usuario.
     * @return El saldo actual del usuario.
     */
    BigDecimal getBalance(int userId);

    /**
     * Realiza un depósito en la cuenta de un usuario.
     * @param userId El identificador único del usuario.
     * @param amount El monto a depositar.
     * @return true si el depósito fue exitoso, false si ocurrió algún error.
     */
    boolean deposit(int userId, BigDecimal amount);

    /**
     * Realiza un retiro de la cuenta de un usuario.
     * @param userId El identificador único del usuario.
     * @param amount El monto a retirar.
     * @return true si el retiro fue exitoso, false si ocurrió algún error (saldo insuficiente, etc.).
     */
    boolean withdraw(int userId, BigDecimal amount);

}
