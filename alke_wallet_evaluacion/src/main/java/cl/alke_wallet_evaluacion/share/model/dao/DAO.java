package cl.alke_wallet_evaluacion.share.model.dao;
/**
 * Importaciones necesarias para la implementacion del codigo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase abstracta que representa un DAO genérico para la interacción con la base de datos.
 */
public abstract class DAO {

    public Connection conn;
    public ResultSet resultSet;
    public Statement stmt;

    /**
     * Obtiene una conexión a la base de datos.
     * @return La conexión establecida.
     * @throws SQLException Si ocurre un error al conectar a la base de datos.
     */
    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/alke_wallet";
            String user = "root";
            String pass = "KVLr5891";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Conectado a la Base de Datos");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: Driver no encontrado");
                throw new SQLException("Error: Driver no encontrado", ex);
            }
        }
        return conn;
    }
    
    /**
     * Ejecuta una consulta SQL y devuelve el conjunto de resultados.
     * @param sql La consulta SQL a ejecutar.
     * @return El ResultSet que contiene los resultados de la consulta.
     */
    public ResultSet consultar(String sql) {
        try {
            getConnection();
            this.stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            close(conn, stmt, resultSet);
            return resultSet;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            return null;
        }
    }
    
    /**
     * Ejecuta una sentencia SQL que modifica la base de datos (INSERT, UPDATE, DELETE).
     * @param sql La sentencia SQL a ejecutar.
     * @return El número de filas afectadas por la sentencia.
     */
    public int ejecutarSql(String sql) {
        try {
            getConnection();
            this.stmt = conn.createStatement();
            int regModificados = stmt.executeUpdate(sql);
            close(conn, stmt, resultSet);
            return regModificados;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            return 0;
        }
    }

    /**
     * Cierra la conexión a la base de datos y libera los recursos.
     * @param conn La conexión a cerrar.
     * @param stmt El Statement a cerrar.
     * @param rs El ResultSet a cerrar.
     */
    public void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}