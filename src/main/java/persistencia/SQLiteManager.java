/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase SQLiteManager se encarga manejar la apertura y cierre de la base de
 * datos. Dando metodos para abrir y cerrar la conexion de manera segura.
 *
 * @author Marcos Ramon Caraballo, Angelina María Vialle, Valentin Rebechi, Ian
 * Caraballo.
 * @version 27/10/2024
 */
public class SQLiteManager {

    private Connection connection;

    /**
     * Este metodo se encarga de establecer la conexion con la base de datos. Si
     * logra conectarse devuelve la conexion.
     *
     * @return la conexion establecida a la base de datos en caso de que se haya
     * podido, o el objeto nulo en caso de error.
     */
    public Connection getConexion() {
        try {
            // Establece la URL de conexión a la base de datos SQLite
            String url = "jdbc:sqlite:Restaurante.db";

            // Conecta a la base de datos
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Se encarga de cerrar la conexion a la base de datos. Si hay una conexion
     * a la base de datos la cierra.
     */
    public void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
