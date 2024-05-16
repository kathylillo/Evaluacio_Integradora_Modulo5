package cl.alke_wallet_evaluacion.dao;


import java.math.BigDecimal;

import cl.alke_wallet_evaluacion.model.User;

/**
 * Interfaz que define las operaciones CRUD para los usuarios en el sistema.
 */
public interface UserDao {

    /**
     * Obtiene un usuario por su identificador único.
     * @param userId El identificador único del usuario.
     * @return El usuario correspondiente al identificador especificado, o null si no se encuentra.
     */
    User getUserById(int userId);

    /**
     * Obtiene un usuario por su nombre de usuario.
     * @param username El nombre de usuario del usuario.
     * @return El usuario correspondiente al nombre de usuario especificado, o null si no se encuentra.
     */
    User getUserByUsername(String username);

    /**
     * Agrega un nuevo usuario al sistema.
     * @param user El usuario que se desea agregar.
     * @return true si el usuario se agregó correctamente, false si ocurrió algún error.
     */
    boolean addUser(User user);

    /**
     * Actualiza el saldo de un usuario específico.
     * @param userId El identificador único del usuario cuyo saldo se desea actualizar.
     * @param newBalance El nuevo saldo del usuario.
     * @return true si se actualizó el saldo correctamente, false si ocurrió algún error.
     */
    boolean updateUserBalance(int userId, BigDecimal newBalance); 
}
