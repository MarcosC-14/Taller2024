/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class SQLiteManager {

    private static Connection connection;

    public static Connection getConexion() {
        if(connection == null){
            try {
                // Establece la URL de conexión a la base de datos SQLite
                String url = "jdbc:sqlite:Restaurante.db";

                // Conecta a la base de datos
                connection = DriverManager.getConnection(url);
                System.out.println("Conexión exitosa a la base de datos SQLite.");
            } catch (SQLException e) {
                System.err.println("Error de conexión: " + e.getMessage());
            }
        }
        return connection;
    }

    // Método para cerrar la conexión a la base de datos
    public void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
