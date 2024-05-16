package cl.alke_wallet_evaluacion.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Clase que representa una transacción realizada por un usuario en el sistema.
 */
public class Transaction {

    private int id;
    private int userId;
    private BigDecimal amount;
    private int typeId; 
    private LocalDateTime dateTime;
    private String transactionType;

    /**
     * Constructor vacío de la clase Transaction.
     */
    public Transaction() {}

    /**
     * Constructor de la clase Transaction.
     * @param id Identificador único de la transacción.
     * @param userId Identificador único del usuario que realizó la transacción.
     * @param amount Monto de la transacción.
     * @param typeId Identificador del tipo de transacción.
     * @param dateTime Fecha y hora en la que se realizó la transacción.
     */
    public Transaction(int id, int userId, BigDecimal amount, int typeId, LocalDateTime dateTime) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.typeId = typeId;
        this.dateTime = dateTime;
    }

    /**
     * Obtiene el identificador único de la transacción.
     * @return El identificador único de la transacción.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la transacción.
     * @param id El nuevo identificador único de la transacción.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador único del usuario que realizó la transacción.
     * @return El identificador único del usuario.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Establece el identificador único del usuario que realizó la transacción.
     * @param userId El nuevo identificador único del usuario.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Obtiene el monto de la transacción.
     * @return El monto de la transacción.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Establece el monto de la transacción.
     * @param amount El nuevo monto de la transacción.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Obtiene el identificador del tipo de transacción.
     * @return El identificador del tipo de transacción.
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Establece el identificador del tipo de transacción.
     * @param typeId El nuevo identificador del tipo de transacción.
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    /**
     * Obtiene la fecha y hora en la que se realizó la transacción.
     * @return La fecha y hora de la transacción.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Establece la fecha y hora de la transacción.
     * @param dateTime La nueva fecha y hora de la transacción.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    /**
     * Obtiene el tipo de la transacción.
     * @return El tipo de la transacción.
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Establece el tipo de la transacción.
     * @param transactionType El nuevo tipo de la transacción.
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}